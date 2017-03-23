package com.test.DAO;

import com.test.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class UserDao {
    private static Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
    private static SessionFactory sessionFactory = cfg.buildSessionFactory();



    public void insert(User user) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


    public User selectUser(String userId){
        User user = new User();
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            user = (User) session.get(User.class, userId);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return user;
    }

        public boolean userIdExists(String userId){

        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Criteria userIdSearch = session.createCriteria(User.class)
                    .add(Restrictions.eq("userId", userId))
                    .setProjection(Projections.rowCount());
            Long rowCount = (Long) userIdSearch.uniqueResult();
            return (rowCount > 0);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return false;
    }
}

