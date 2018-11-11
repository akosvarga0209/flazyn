package com.flazyn.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Double balance;
    @Column
    private Integer age;
    @Column
    private Boolean enabled;
    @Column
    private String activation;
    @Column
    private String fullName;
    @Column
    private String profilePicture;
    @Column
    private Integer minPrice;
    @Column
    private Integer maxPrice;
    @Column
    private Boolean pet;
    @Column
    private Boolean smoke;
    @Column
    private String location;
    @Column
    private Boolean sharedRoom;
    @Column
    private Boolean furnished;
    @Column
    private Integer maxNumberOfRoomMates;
    @Column
    private Boolean roomMateSearch;
    @Column
    private String fbId;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name ="users_roles",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name ="users_natures",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="nature_id")}
    )
    private Set<Nature> natures = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name ="users_tasks",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="task_id")}
    )
    private Set<Nature> tasks = new HashSet<>();

    @OneToMany(
            mappedBy = "interestedUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserMatch> likedUsers = new ArrayList<>();


    @OneToMany(
            mappedBy = "likedUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserMatch> interestedUsers = new ArrayList<>();


    @OneToMany(
            mappedBy = "interestedUser",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserAdvertisementMatch> likedAdvertisement = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Flat> flats;

    @ManyToOne
    @JoinColumn(name = "COMMUNITY_ID")
    private Community community;

    @OneToMany(mappedBy = "toUser")
    private List<Message> inboxMessages;

    @OneToMany(mappedBy = "fromUser")
    private List<Message> outboxMessages;

    @OneToMany(mappedBy = "user")
    private List<Payment> payments;

    @OneToMany(mappedBy = "user")
    private List<Event> events;

    @OneToMany(mappedBy = "user")
    private List<Advertisement> advertisements;

    @OneToMany(mappedBy = "user")
    private List<Image> images;

    public User() {
    }

    public User(String username, String password){
        this.email = username;
        this.password = password;
    }

    public User(String username, String password, Double salary, int age, Set<Role> roles) {
        this.email = username;
        this.password = password;
        this.balance = salary;
        this.age = age;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public String getActivation() {
        return activation;
    }

    public void setActivation(String activation) {
        this.activation = activation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Integer getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getSharedRoom() {
        return sharedRoom;
    }

    public void setSharedRoom(Boolean sharedRoom) {
        this.sharedRoom = sharedRoom;
    }

    public Boolean getFurnished() {
        return furnished;
    }

    public void setFurnished(Boolean furnished) {
        this.furnished = furnished;
    }

    public Integer getMaxNumberOfRoomMates() {
        return maxNumberOfRoomMates;
    }

    public void setMaxNumberOfRoomMates(Integer maxNumberOfRoomMates) {
        this.maxNumberOfRoomMates = maxNumberOfRoomMates;
    }

    public Boolean getRoomMateSearch() {
        return roomMateSearch;
    }

    public void setRoomMateSearch(Boolean roomMateSearch) {
        this.roomMateSearch = roomMateSearch;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Set<Nature> getNatures() {
        return natures;
    }

    public void setNatures(Set<Nature> natures) {
        this.natures = natures;
    }

    public Set<Nature> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Nature> tasks) {
        this.tasks = tasks;
    }

    public List<UserMatch> getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(List<UserMatch> likedUsers) {
        this.likedUsers = likedUsers;
    }

    public List<UserMatch> getInterestedUsers() {
        return interestedUsers;
    }

    public void setInterestedUsers(List<UserMatch> interestedUsers) {
        this.interestedUsers = interestedUsers;
    }

    public List<UserAdvertisementMatch> getLikedFlat() {
        return likedAdvertisement;
    }

    public void setLikedFlat(List<UserAdvertisementMatch> likedFlat) {
        this.likedAdvertisement = likedFlat;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    public Community getCommunity() {
        return community;
    }

    public void setCommunity(Community community) {
        this.community = community;
    }

    public List<Message> getInboxMessages() {
        return inboxMessages;
    }

    public void setInboxMessages(List<Message> inboxMessages) {
        this.inboxMessages = inboxMessages;
    }

    public List<Message> getOutboxMessages() {
        return outboxMessages;
    }

    public void setOutboxMessages(List<Message> outboxMessages) {
        this.outboxMessages = outboxMessages;
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

    public List<Advertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(List<Advertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public void addRoles(String roleName) {
        if (this.roles == null || this.roles.isEmpty()) {
            this.roles = new HashSet<>();
        }
        this.roles.add(new Role(roleName));
    }

    public List<UserAdvertisementMatch> getLikedAdvertisement() {
        return likedAdvertisement;
    }

    public void setLikedAdvertisement(List<UserAdvertisementMatch> likedAdvertisement) {
        this.likedAdvertisement = likedAdvertisement;
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

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                ", age=" + age +
                ", enabled=" + enabled +
                ", activation='" + activation + '\'' +
                ", fullName='" + fullName + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", pet=" + pet +
                ", smoke=" + smoke +
                ", location='" + location + '\'' +
                ", sharedRoom=" + sharedRoom +
                ", furnished=" + furnished +
                ", maxNumberOfRoomMates=" + maxNumberOfRoomMates +
                ", roomMateSearch=" + roomMateSearch +
                ", gender=" + gender +
                ", roles=" + roles +
                ", natures=" + natures +
                ", tasks=" + tasks +
                ", likedUsers=" + likedUsers +
                ", interestedUsers=" + interestedUsers +
                ", likedAdvertisement=" + likedAdvertisement +
                ", flats=" + flats +
                ", community=" + community +
                ", inboxMessages=" + inboxMessages +
                ", outboxMessages=" + outboxMessages +
                ", payments=" + payments +
                ", events=" + events +
                ", advertisements=" + advertisements +
                '}';
    }
}