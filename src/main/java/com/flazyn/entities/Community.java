package com.flazyn.entities;


import javax.persistence.*;
import java.util.List;

@Entity
public class Community extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long balance;

    @OneToMany(mappedBy = "community")
    private List<User> users;

    @OneToMany(mappedBy = "community")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "community")
    private List<Fee> fees;

    @OneToMany(mappedBy = "community")
    private List<ProblemTicket> problemTickets;

    @OneToMany(mappedBy = "community")
    private List<Payment> payments;

    @OneToMany(mappedBy = "community")
    private List<Event> events;

    public Community() {
    }

    public Community(Long balance, List<User> users, List<Contract> contracts, List<Fee> fees) {
        this.balance = balance;
        this.users = users;
        this.contracts = contracts;
        this.fees = fees;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public List<ProblemTicket> getProblemTickets() {
        return problemTickets;
    }

    public void setProblemTickets(List<ProblemTicket> problemTickets) {
        this.problemTickets = problemTickets;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
