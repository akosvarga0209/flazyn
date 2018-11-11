package com.flazyn.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Contract extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Date startDate;
    private Date endDate;
    private Double rentingFee;
    private Boolean confirmed;

    @ManyToOne
    @JoinColumn(name = "FLAT_ID")
    private Flat flat;

    @ManyToOne
    @JoinColumn(name = "COMMUNITY_ID")
    private Community community;

    public Contract() {
    }

    public Contract(Date startDate, Date endDate, Double rentingFee, Boolean confirmed, Flat flat, Community community) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rentingFee = rentingFee;
        this.confirmed = confirmed;
        this.flat = flat;
        this.community = community;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getRentingFee() {
        return rentingFee;
    }

    public void setRentingFee(Double rentingFee) {
        this.rentingFee = rentingFee;
    }

    public Boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed) {
        this.confirmed = confirmed;
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
