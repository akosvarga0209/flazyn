package com.flazyn.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserMatchId implements Serializable {

    @Column(name = "liked_user_id")
    private Long likedUserId;

    @Column(name = "interested_user_id")
    private Long interestedUserId;

    public UserMatchId() {
    }

    public UserMatchId(Long likedUserId, Long interestedUserId) {
        this.likedUserId = likedUserId;
        this.interestedUserId = interestedUserId;
    }

    public Long getLikedUserId() {
        return likedUserId;
    }

    public void setLikedUserId(Long likedUserId) {
        this.likedUserId = likedUserId;
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
        UserMatchId that = (UserMatchId) o;
        return Objects.equals(likedUserId, that.likedUserId) &&
                Objects.equals(interestedUserId, that.interestedUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(likedUserId, interestedUserId);
    }
}
