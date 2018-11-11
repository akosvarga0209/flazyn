package com.flazyn.entities;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class UserAdvertisementMatch extends BaseEntity implements Serializable  {
    @EmbeddedId
    private UserAdvertisementId id;

    @ManyToOne
    @MapsId("likedAdvertisementId")
    @JoinColumn(name = "LIKED_ADVERTISEMENT_ID")
    private Advertisement likedAdvertisement;

    @ManyToOne
    @MapsId("interestedUserId")
    @JoinColumn(name = "INTERESTED_USER_ID")
    private User interestedUser;

    private Boolean accepted;

    public UserAdvertisementMatch() {
    }

    public UserAdvertisementMatch(Advertisement likedAdvertisement, User interestedUser) {
        this.likedAdvertisement = likedAdvertisement;
        this.interestedUser = interestedUser;
        this.id = new UserAdvertisementId(likedAdvertisement.getId(),interestedUser.getId());
        this.accepted = false;
    }

    public void setId(UserAdvertisementId id) {
        this.id = id;
    }

    public Advertisement getLikedAdvertisement() {
        return likedAdvertisement;
    }

    public void setLikedAdvertisement(Advertisement likedAdvertisement) {
        this.likedAdvertisement = likedAdvertisement;
    }

    public User getInterestedUser() {
        return interestedUser;
    }

    public void setInterestedUser(User interestedUser) {
        this.interestedUser = interestedUser;
    }

    public Boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAdvertisementMatch that = (UserAdvertisementMatch) o;
        return Objects.equals(likedAdvertisement, that.likedAdvertisement) &&
                Objects.equals(interestedUser, that.interestedUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(likedAdvertisement, interestedUser);
    }

}
