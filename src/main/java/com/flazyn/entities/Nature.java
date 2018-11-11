package com.flazyn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Nature extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private String description;
    @ManyToMany(mappedBy="natures")
    private Set<User> users = new HashSet<User>();

    public Nature() {
    }

    public Nature(String description){
        this.description = description;
    }

    public Nature(String description, Set<User> users) {
        this.description = description;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
