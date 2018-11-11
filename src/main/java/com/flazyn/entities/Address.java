package com.flazyn.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Address extends BaseEntity {

    @Id
    @GeneratedValue
    private Long id;
    @NotNull
    private String country;
    @NotNull
    private String state;
    @NotNull
    private String city;
    private String zipCode;
    @NotNull
    private String street;
    private String type;
    private String building;
    private Integer floor;
    private String door;
    private Double longitude;
    private Double latidue;
    @OneToMany(mappedBy = "address")
    private List<Flat> flats;
    @OneToMany(mappedBy = "address")
    private List<Advertisement> advertisements;

    public Address() {
    }

    public Address(String country, String state, String city, String zipCode, String street, String type, String building, Integer floor, String door, List<Flat> flats) {
        this.country = country;
        this.state = state;
        this.city = city;
        this.zipCode = zipCode;
        this.street = street;
        this.type = type;
        this.building = building;
        this.floor = floor;
        this.door = door;
        this.flats = flats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public String getDoor() {
        return door;
    }

    public void setDoor(String door) {
        this.door = door;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatidue() {
        return latidue;
    }

    public void setLatidue(Double latidue) {
        this.latidue = latidue;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }
}
