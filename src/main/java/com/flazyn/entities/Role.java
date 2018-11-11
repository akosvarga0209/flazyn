package com.flazyn.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Role extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    String name;

    @ManyToMany(mappedBy="roles")
    private Set<User> users = new HashSet<User>();

    Role() {}

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
