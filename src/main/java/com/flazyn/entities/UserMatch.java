package com.flazyn.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "user_match")
public class UserMatch extends BaseEntity implements Serializable {

    @EmbeddedId
    private UserMatchId id;

    @ManyToOne
    @MapsId("likedUserId")
    @JoinColumn(name = "LIKED_USER_ID")
    private User likedUser;

    @ManyToOne
    @MapsId("interestedUserId")
    @JoinColumn(name = "INTERESTED_USER_ID")
    private User interestedUser;

    private Boolean accepted;

    public UserMatch() {
    }

    public UserMatch(User likedUser, User interestedUser) {
        this.likedUser = likedUser;
        this.interestedUser = interestedUser;
        this.id = new UserMatchId(likedUser.getId(), interestedUser.getId());
        this.accepted = false;
    }

    public User getLikedUser() {
        return likedUser;
    }

    public void setLikedUser(User likedUser) {
        this.likedUser = likedUser;
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
        UserMatch userMatch = (UserMatch) o;
        return Objects.equals(likedUser, userMatch.likedUser) &&
                Objects.equals(interestedUser, userMatch.interestedUser);
    }

    @Override
    public int hashCode() {

        return Objects.hash(likedUser, interestedUser);
    }
}
