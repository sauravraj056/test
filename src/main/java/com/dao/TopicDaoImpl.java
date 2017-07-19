package com.dao;

import com.model.Topic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicDaoImpl implements TopicDao {

    @Autowired(required = true)
    SessionFactory sessionFactory;
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }



    public boolean addTopic(Topic topic) {
        try{
            Session session=sessionFactory.openSession();
            session.beginTransaction();
            session.save(topic);
            session.getTransaction().commit();
            session.close();


        return true;
        }
        catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
