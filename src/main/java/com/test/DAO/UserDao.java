package com.test.DAO;

import com.test.model.UserEntity;
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


    public void insert(UserEntity user) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(UserEntity user) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();

    }

    public void select(UserEntity user){

        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria c = session.createCriteria(UserEntity.class);
    }

    public boolean userIdExists(String userId) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria userIdSearch = session.createCriteria(UserEntity.class);
        userIdSearch.add(Restrictions.eq("UserID", userId));
        userIdSearch.setProjection(Projections.rowCount());
        Long rowCount = (Long) userIdSearch.uniqueResult();
        return (rowCount > 0);
    }
}

