package com.flazyn.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Payment extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date date;
    private String description;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;
    @ManyToOne
    @JoinColumn(name = "COMMUNITY")
    private Community community;
    @ManyToOne
    @JoinColumn(name = "FLAT_ID")
    private Flat flat;
    //TODO? relation with fee??

    public Payment() {
    }

    public Payment(Date date, String description, User user, Community community, Flat flat) {
        this.date = date;
        this.description = description;
        this.user = user;
        this.community = community;
        this.flat = flat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }
}
