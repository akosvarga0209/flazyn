package com.flazyn.entities;

import javax.persistence.*;

@Entity
public class ProblemTicket extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "FLAT_ID")
    private Flat flat;

    @ManyToOne
    @JoinColumn(name = "COMMUNITY_ID")
    private Community community;

    public ProblemTicket() {
    }

    public ProblemTicket(String description, Flat flat, Community community) {
        this.description = description;
        this.flat = flat;
        this.community = community;
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

    public Flat getFlat() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat = flat;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }
}
