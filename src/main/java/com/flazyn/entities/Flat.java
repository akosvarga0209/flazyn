package com.flazyn.entities;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Flat extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Double balance;
    private Boolean available;
    private Integer maxResidentCount;
    private Double rentingFee;
    private Boolean pet;
    private Boolean smoke;
    private Float size;
    private Boolean furnished;
    private Integer floor;
    private Double commonCost;
    private Double utilities;
    private Boolean elevator;
    @Enumerated(value = EnumType.STRING)
    private ViewType viewType;

    @OneToMany(mappedBy = "flat")
    private List<Fee> fees;

    @OneToMany(mappedBy = "flat")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "flat")
    private List<ProblemTicket> problemTickets;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @OneToMany(mappedBy = "flat")
    private List<Payment> payments;

    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @OneToMany(mappedBy = "flat")
    private List<Image> images;

    public Flat() {
    }

    public Flat(Double balance, Boolean available, Integer maxResidentCount, Double rentingFee, Boolean pet, Boolean smoke) {
        this.balance = balance;
        this.available = available;
        this.maxResidentCount = maxResidentCount;
        this.rentingFee = rentingFee;
        this.pet = pet;
        this.smoke = smoke;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Integer getMaxResidentCount() {
        return maxResidentCount;
    }

    public void setMaxResidentCount(Integer maxResidentCount) {
        this.maxResidentCount = maxResidentCount;
    }

    public Double getRentingFee() {
        return rentingFee;
    }

    public void setRentingFee(Double rentingFee) {
        this.rentingFee = rentingFee;
    }

    public Boolean getPet() {
        return pet;
    }

    public void setPet(Boolean pet) {
        this.pet = pet;
    }

    public Boolean getSmoke() {
        return smoke;
    }

    public void setSmoke(Boolean smoke) {
        this.smoke = smoke;
    }

    public List<Fee> getFees() {
        return fees;
    }

    public void setFees(List<Fee> fees) {
        this.fees = fees;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public List<ProblemTicket> getProblemTickets() {
        return problemTickets;
    }

    public void setProblemTickets(List<ProblemTicket> problemTickets) {
        this.problemTickets = problemTickets;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Double getCommonCost() {
        return commonCost;
    }

    public void setCommonCost(Double commonCost) {
        this.commonCost = commonCost;
    }

    public Double getUtilities() {
        return utilities;
    }

    public void setUtilities(Double utilities) {
        this.utilities = utilities;
    }

    public Boolean getElevator() {
        return elevator;
    }

    public void setElevator(Boolean elevator) {
        this.elevator = elevator;
    }

    public ViewType getViewType() {
        return viewType;
    }

    public void setViewType(ViewType viewType) {
        this.viewType = viewType;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void addImage(Image image){
        if (this.images == null || this.images.isEmpty()){
            this.images = new ArrayList<>();
        }
        this.images.add(image);
    }
}
