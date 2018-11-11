package com.flazyn.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Advertisement extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Integer price;
    private Boolean pet;
    private Boolean smoke;
    private Float size;
    private Double rentingFee;
    private Integer roomNumber;
    private Boolean hasDeposit;
    private Integer deposit;
    @Lob
    private String description;
    @Enumerated(value = EnumType.STRING)
    private AdCategory adCategory;
    @ManyToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    @ManyToOne
    @JoinColumn(name="USER_ID")
    private User user;
    private Boolean active;
    private Integer minRentMonth;
    private Boolean readyToMove;

    @OneToMany(
            mappedBy = "likedAdvertisement",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserAdvertisementMatch> interestedUsers = new ArrayList<>();

    @OneToMany(mappedBy = "advertisement")
    private List<Image> images;

    public Advertisement() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    public Float getSize() {
        return size;
    }

    public void setSize(Float size) {
        this.size = size;
    }

    public Double getRentingFee() {
        return rentingFee;
    }

    public void setRentingFee(Double rentingFee) {
        this.rentingFee = rentingFee;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Boolean getHasDeposit() {
        return hasDeposit;
    }

    public void setHasDeposit(Boolean hasDeposit) {
        this.hasDeposit = hasDeposit;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AdCategory getAdCategory() {
        return adCategory;
    }

    public void setAdCategory(AdCategory adCategory) {
        this.adCategory = adCategory;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<UserAdvertisementMatch> getInterestedUsers() {
        return interestedUsers;
    }

    public void setInterestedUsers(List<UserAdvertisementMatch> interestedUsers) {
        this.interestedUsers = interestedUsers;
    }

    public Integer getMinRentMonth() {
        return minRentMonth;
    }

    public void setMinRentMonth(Integer minRentMonth) {
        this.minRentMonth = minRentMonth;
    }

    public Boolean getReadyToMove() {
        return readyToMove;
    }

    public void setReadyToMove(Boolean readyToMove) {
        this.readyToMove = readyToMove;
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

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", price=" + price +
                ", pet=" + pet +
                ", smoke=" + smoke +
                ", size=" + size +
                ", rentingFee=" + rentingFee +
                ", roomNumber=" + roomNumber +
                ", hasDeposit=" + hasDeposit +
                ", deposit=" + deposit +
                ", description='" + description + '\'' +
                ", flatCategory=" + adCategory +
                ", address=" + address +
                '}';
    }
}
