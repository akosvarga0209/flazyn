package com.flazyn.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Event extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String description;
    private Date startDate;
    private Date endDate;

    @ManyToOne
    @JoinColumn(name = "COMMUNITY_ID")
    private Community community;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Event() {
    }

    public Event(String description, Date startDate, Date endDate, Community community, User user) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.community = community;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
