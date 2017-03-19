package com.test.DAO;

import com.test.model.JeanTemplate;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class TemplateDao {

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
        session.saveOrUpdate(jeanTemplate);
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

    public String selectSearchJeanType(String userId) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        List results = session.createCriteria(JeanTemplate.class)
                .add(Restrictions.eq("userId", userId))
                .setProjection(Projections.projectionList()
                        .add(Projections.rowCount(), "count")
                        .add(Projections.groupProperty("jeanStyle")))
                .addOrder(Order.desc("count"))
                .setMaxResults(1)
                .list();
        session.close();

        Object[] array = (Object[]) results.get(0);

        return (String) array[1];
    }

    public Object[] selectSearchCroppedDistressed(String userId){
        Object[] preferences = new Object[3];

        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        Criteria total = session.createCriteria(JeanTemplate.class)
                .add(Restrictions.eq("userId", userId))
                .setProjection(Projections.rowCount());
        Long rowCount = (Long) total.uniqueResult();

        Criteria c1 = session.createCriteria(JeanTemplate.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("cropped", (byte) 1))
                .setProjection(Projections.rowCount());
        Long countCropped = (Long) c1.uniqueResult();

        Criteria c2 = session.createCriteria(JeanTemplate.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("distressed", (byte) 1))
                .setProjection(Projections.rowCount());
        Long countDistressed = (Long) c2.uniqueResult();



        preferences[0] = (countCropped > (rowCount - countCropped));
        preferences[1] = (countDistressed > (rowCount - countDistressed));
        preferences[2] = (countCropped > countDistressed);

        return preferences;

    }



    }

        /*Object[] array = (Object[]) o;


        long max = 0;
        for(Object o: results){



            for (int i=0; i < array.length; i++){
                long count = (long) array[0];
                if ((long) array[i] > max){
                    max = (long) array[i];
                    secondMax = max;
                    max = numbers[i];
                }else if (numbers[i] > secondMax) {
                    secondMax = numbers[i];
                }
            }
            return secondMax;
        }



        long count = (long) array[0];



        System.out.println("" + name + count);



     *//*   for(Object o: results){
            long num = (long) o[0];
            String name = (String) o[1];
        }

        String m = (String) results.get(0);*/



