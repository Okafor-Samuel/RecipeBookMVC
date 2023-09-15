package com.example.bookrecipe.Model.Entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String username;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userEmail;

    @Column(name = "user_facebook")
    private String userFacebook;
    @Column(name = "user_instagram")
    private String userInstagram;
    @Column(name = "user_tiktok")
    private String userTiktok;

    @Column(name = "user_profile_img")
    @Lob
    private byte[] userProfileImg;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(
            name = "userfavouriterecipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id")
    )
    private List<Recipe> favouriteRecipe = new ArrayList<>();

    public User() {

    }

    public User(String username, String userFirstName, String userLastName, String userPassword, String userEmail) {
        this.username = username;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserFirstName() {
        return userFirstName;
    }

    public void setUserFirstName(String userFirstName) {
        this.userFirstName = userFirstName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public byte[] getUserProfileImg() {
        return userProfileImg;
    }

    public void setUserProfileImg(byte[] userProfileImg) {
        this.userProfileImg = userProfileImg;
    }

    public List<Recipe> getFavouriteRecipe() {
        return favouriteRecipe;
    }

    public void setFavouriteRecipe(List<Recipe> favouriteRecipe) {
        this.favouriteRecipe = favouriteRecipe;
    }


    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getUserFacebook() {
        return userFacebook;
    }

    public void setUserFacebook(String userFacebook) {
        this.userFacebook = userFacebook;
    }

    public String getUserInstagram() {
        return userInstagram;
    }

    public void setUserInstagram(String userInstagram) {
        this.userInstagram = userInstagram;
    }

    public String getUserTikTok() {
        return userTiktok;
    }

    public void setUserTikTok(String userTiktok) {
        this.userTiktok = userTiktok;
    }
}
