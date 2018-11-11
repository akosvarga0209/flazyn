package com.flazyn.dto;

import com.flazyn.entities.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

public class UserProfileDTO {
    private Boolean roomMateSearch;
    private Integer age;
    @Enumerated(value = EnumType.ORDINAL)
    private Gender gender;
    private Boolean pet;
    private Integer priceMin;
    private Integer priceMax;
    private String location;
    private Set<NatureDTO> nature;
    private Integer maxNumberOfRoomMates;
    private Boolean furnished;
    private Boolean smoke;
    private Boolean sharedRoom;

    public UserProfileDTO() {
    }

    public Boolean getRoomMateSearch() {
        return roomMateSearch;
    }

    public void setRoomMateSearch(Boolean roomMateSearch) {
        this.roomMateSearch = roomMateSearch;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Boolean getPet() {
        return pet;
    }

    public void setPet(Boolean pet) {
        this.pet = pet;
    }

    public Integer getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Integer priceMin) {
        this.priceMin = priceMin;
    }

    public Integer getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Integer priceMax) {
        this.priceMax = priceMax;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Set<NatureDTO> getNature() {
        return nature;
    }

    public void setNature(Set<NatureDTO> nature) {
        this.nature = nature;
    }

    public Integer getMaxNumberOfRoomMates() {
        return maxNumberOfRoomMates;
    }

    public void setMaxNumberOfRoomMates(Integer maxNumberOfRoomMates) {
        this.maxNumberOfRoomMates = maxNumberOfRoomMates;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Boolean getSmoke() {
        return smoke;
    }

    public void setSmoke(Boolean smoke) {
        this.smoke = smoke;
    }

    public Boolean getSharedRoom() {
        return sharedRoom;
    }

    public void setSharedRoom(Boolean sharedRoom) {
        this.sharedRoom = sharedRoom;
    }
}
