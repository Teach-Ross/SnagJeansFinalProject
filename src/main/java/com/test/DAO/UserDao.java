package com.test.DAO;

import com.test.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDao {
    private SessionFactory sessionFactory;
    private Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

    public SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        return cfg.buildSessionFactory();
    }


    public void insert(User user) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }




    public User selectUser(String userId){
        User user = new User();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        user = (User) session.get(User.class, userId);
        session.close();
        return user;
    }

        public boolean userIdExists(String userId){

        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria userIdSearch = session.createCriteria(User.class)
                .add(Restrictions.eq("userId", userId))
                .setProjection(Projections.rowCount());
        Long rowCount = (Long) userIdSearch.uniqueResult();
        session.close();

        return (rowCount > 0);

    }
}

