package com.test.DAO;

import com.test.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;

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

    public void delete(User user) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

    }

    public void select(User user){

        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria c = session.createCriteria(User.class);
    }

    public boolean userIdExists(String userId) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria userIdSearch = session.createCriteria(User.class);
        ArrayList<User> customerList = (ArrayList<User>) userIdSearch.list();
        String testNewUserID = "567890123456";
        for(User user: customerList){
            if(user.getUserId().equals(testNewUserID)){
                return true;
            }
        }

        return false;

    }

        public boolean userIdExistsTwo(String userId){

        SessionFactory sessionFactory;
        Configuration cfg2 = new Configuration().configure("hibernate.cfg.xml");
        SessionFactory sessionFactory1 = cfg2.buildSessionFactory();
        Session session = sessionFactory1.openSession();
        session.beginTransaction();
        Criteria userIdSearch = session.createCriteria(User.class)
                .add(Restrictions.eq("userId", userId))
                .setProjection(Projections.rowCount());

            Long rowCount = (Long) userIdSearch.uniqueResult();
            return (rowCount > 0);

    }
}

