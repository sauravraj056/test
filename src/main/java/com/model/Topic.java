package com.model;


import jdk.Exported;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String topicname;
    Date dateCreated;
    Date lastUpdated;
    enum Visblity {Public,Private;}

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", topicname='" + topicname + '\'' +
                ", dateCreated=" + dateCreated +
                ", lastUpdated=" + lastUpdated +
                ", user=" + user +
                '}';
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopicname() {
        return topicname;
    }

    public void setTopicname(String topicname) {
        this.topicname = topicname;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }





    @OneToMany

    @JoinTable(name="User")
    private User user;
    public Topic(String topicname, Date dateCreated, Date lastUpdated, User user) {
        this.topicname = topicname;
        this.dateCreated = dateCreated;
        this.lastUpdated = lastUpdated;
        this.user = user;
    }
}
