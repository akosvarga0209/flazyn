package com.flazyn.entities;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Task  extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String description;
    private Date dueDate;
    private Date dueTo;
    private Date endOfValidity;
    private Integer iterationInterval;
    private Boolean completed;
    @ManyToMany(mappedBy="tasks")
    private Set<User> users = new HashSet<User>();

    public Task() {
    }

    public Task(String description, Date dueDate, Date dueTo, Date endOfValidity, Integer iterationInterval, Boolean completed, Set<User> users) {
        this.description = description;
        this.dueDate = dueDate;
        this.dueTo = dueTo;
        this.endOfValidity = endOfValidity;
        this.iterationInterval = iterationInterval;
        this.completed = completed;
        this.users = users;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueTo() {
        return dueTo;
    }

    public void setDueTo(Date dueTo) {
        this.dueTo = dueTo;
    }

    public Date getEndOfValidity() {
        return endOfValidity;
    }

    public void setEndOfValidity(Date endOfValidity) {
        this.endOfValidity = endOfValidity;
    }

    public Integer getIterationInterval() {
        return iterationInterval;
    }

    public void setIterationInterval(Integer iterationInterval) {
        this.iterationInterval = iterationInterval;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
