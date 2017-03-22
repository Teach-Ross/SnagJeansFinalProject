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
    private Configuration cfg = new Configuration().configure("hibernate.cfg.xml");
    private SessionFactory sessionFactory = cfg.buildSessionFactory();



    public void insert(JeanTemplate jeanTemplate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(jeanTemplate);
        session.getTransaction().commit();
        session.close();
    }

    public void delete(JeanTemplate jeanTemplate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(jeanTemplate);
        session.getTransaction().commit();
        session.close();
    }



    public void update(JeanTemplate jeanTemplate, int templateId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        jeanTemplate.setTemplateId(templateId);
        session.saveOrUpdate(jeanTemplate);
        session.getTransaction().commit();
        session.close();
    }

    public JeanTemplate selectTemplate(int templateId) {
        JeanTemplate temp = new JeanTemplate();
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        temp = (JeanTemplate) session.get(JeanTemplate.class, templateId);
        session.close();
        return temp;
    }
    /*  Method returns arraylist with all templates matching userId
        @param userId identifies user in database and links to their templates
     */
    public ArrayList<JeanTemplate> selectAllUserTemplates(String userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Criteria c = session.createCriteria(JeanTemplate.class)
                .add(Restrictions.eq("userId", userId));
        //casts c.list to arraylist
        ArrayList<JeanTemplate> templateList = (ArrayList<JeanTemplate>) c.list();
        session.close();

        return templateList;
    }
    /*  Method returns most common value for JeanType for a user's existing templates
        @param userId identifies user in database and links to their templates
     */
    public String selectSearchJeanType(String userId) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List results = session.createCriteria(JeanTemplate.class)
                //gathers all template entries matching userId
                .add(Restrictions.eq("userId", userId))
                .setProjection(Projections.projectionList()
                        //groups entries together by JeanStyle
                        .add(Projections.groupProperty("jeanStyle"))
                        //counts number of entries in each group
                        .add(Projections.rowCount(), "count"))
                // orders groups by descending results
                .addOrder(Order.desc("count"))
                //returns top result
                .setMaxResults(1)
                .list();
        session.close();

        //accounts users with no templates, return empty string
        if (results.isEmpty()) {
            return "";
        }

        /*  gather this list entry by casting it to an Object[]
            Object[] array contains name of common style JeanStyle and number of times it occurs
         */
        Object[] array = (Object[]) results.get(0);

        /*  first entry in this array contains the name of the users most popular JeanStyle
            again must cast into a String object as Hibernate only returns general java objects
         */
        return (String) array[0];
    }

    /*  Method returns most common user selections for cropped and distressed
        Also identifies whether which of these two occurs more often
        Return Object[] with these three values as booleans
        @param userId identifies user in database and links to their templates
     */
    public Object[] selectSearchCroppedDistressed(String userId) {
        Object[] preferences = new Object[3];
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        //counts total number of jean templates entries in the database
        Criteria total = session.createCriteria(JeanTemplate.class)
                //gathers all template entries matching userId
                .add(Restrictions.eq("userId", userId))
                //counts number of rows
                .setProjection(Projections.rowCount());
        //assigns this row count
        Long rowCount = (Long) total.uniqueResult();

        //counts number of jean templates entries with cropped selection
        Criteria c1 = session.createCriteria(JeanTemplate.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("cropped", (byte) 1))
                .setProjection(Projections.rowCount());
        //assigns this row count
        Long countCropped = (Long) c1.uniqueResult();

        //counts number of jean templates entries with distressed selections
        Criteria c2 = session.createCriteria(JeanTemplate.class)
                .add(Restrictions.eq("userId", userId))
                .add(Restrictions.eq("distressed", (byte) 1))
                .setProjection(Projections.rowCount());
        Long countDistressed = (Long) c2.uniqueResult();

        //returns boolean value true if number of entries with cropped is higher than 50% of total entries
        preferences[0] = (countCropped > (rowCount - countCropped));
        //returns boolean value true if number of entries with distressed is higher than 50% of total entries
        preferences[1] = (countDistressed > (rowCount - countDistressed));
        //returns boolean value true if number of entries with cropped is higher than entries with distressed
        preferences[2] = (countCropped > countDistressed);
        session.close();

        //returns these values in an array
        return preferences;

    }
}




