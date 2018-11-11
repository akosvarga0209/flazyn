package com.flazyn.entities;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Message extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String title;
    private String content;
    private Date sentDate;
    @ManyToOne
    @JoinColumn(name = "USER_TO_ID")
    private User toUser;
    @ManyToOne
    @JoinColumn(name = "USER_FROM_ID")
    private User fromUser;

    public Message() {
    }

    public Message(String title, String content, Date sentDate, User toUser, User fromUser) {
        this.title = title;
        this.content = content;
        this.sentDate = sentDate;
        this.toUser = toUser;
        this.fromUser = fromUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSentDate() {
        return sentDate;
    }

    public void setSentDate(Date sentDate) {
        this.sentDate = sentDate;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }
}
