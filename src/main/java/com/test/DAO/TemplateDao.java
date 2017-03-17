package com.test.DAO;

import com.test.model.JeanTemplate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;

public class TemplateDao {
    private SessionFactory sessionFactory;
    private Configuration cfg = new Configuration().configure("hibernate.cfg.xml");

    public SessionFactory getSessionFactory() {
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
        return cfg.buildSessionFactory();
    }


    public void insert(JeanTemplate jeanTemplate) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(jeanTemplate);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(JeanTemplate jeanTemplate) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(jeanTemplate);
        session.getTransaction().commit();
        session.close();
    }

    public void update(JeanTemplate jeanTemplate, int templateId){
        Session session =getSessionFactory().openSession();
        session.beginTransaction();
        jeanTemplate.setTemplateId(templateId);
        JeanTemplate temp = (JeanTemplate) session.get(JeanTemplate.class, templateId);
        temp = jeanTemplate;
        session.saveOrUpdate(temp);
        session.getTransaction().commit();
        session.close();
    }

    public JeanTemplate selectTemplate(int templateId){
        JeanTemplate temp = new JeanTemplate();
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        temp = (JeanTemplate) session.get(JeanTemplate.class, templateId);
        session.close();
        return temp;
    }


    public ArrayList<JeanTemplate> selectAllUserTemplates(String userId){
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria c = session.createCriteria(JeanTemplate.class)
                    .add(Restrictions.eq("userId", userId));
        ArrayList<JeanTemplate> templateList = (ArrayList<JeanTemplate>) c.list();
        session.close();

        return templateList;
    }
}
