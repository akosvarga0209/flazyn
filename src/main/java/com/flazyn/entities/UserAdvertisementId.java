package com.flazyn.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserAdvertisementId implements Serializable {

    @Column(name = "liked_advertisement_id")
    private Long likedAdvertisementId;

    @Column(name = "interested_user_id")
    private Long interestedUserId;

    public UserAdvertisementId() {
    }

    public UserAdvertisementId(Long likedAdvertisementId, Long interestedUserId) {
        this.likedAdvertisementId = likedAdvertisementId;
        this.interestedUserId = interestedUserId;
    }

    public Long getLikedAdvertisementId() {
        return likedAdvertisementId;
    }

    public void setLikedAdvertisementId(Long likedAdvertisementId) {
        this.likedAdvertisementId = likedAdvertisementId;
    }

    public Long getInterestedUserId() {
        return interestedUserId;
    }

    public void setInterestedUserId(Long interestedUserId) {
        this.interestedUserId = interestedUserId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAdvertisementId that = (UserAdvertisementId) o;
        return Objects.equals(likedAdvertisementId, that.likedAdvertisementId) &&
                Objects.equals(interestedUserId, that.interestedUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(likedAdvertisementId, interestedUserId);
    }
}
