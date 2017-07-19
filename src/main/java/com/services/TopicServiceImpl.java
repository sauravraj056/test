package com.services;

import com.dao.TopicDao;
import com.model.Topic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicDao topicDao;
    private void setTopicDao(TopicDao topicDao){this.topicDao=topicDao;}


    public boolean addTopic(Topic topic) {
        boolean check;
        try{
        topic.setDateCreated(new Date());
        topic.setLastUpdated(new Date());
        check=topicDao.addTopic(topic);
        return check;

        }
        catch(Exception e)
        {
            System.out.println(e);}
            return false;

}}
