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

    public boolean emailExists(String email) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria emailSearch = session.createCriteria(UserEntity.class);
        emailSearch.add(Restrictions.eq("email", email).ignoreCase());
        emailSearch.setProjection(Projections.rowCount());
        Long rowCount = (Long) emailSearch.uniqueResult();
        return (rowCount > 0);
    }
}

