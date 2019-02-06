package model.User;

import model.Kweet.Kweet;
import model.Model;

import java.util.ArrayList;
import java.util.Collection;

public class User extends Model {

    //region Fields
    private String userName;
    private String password;
    private String biography;
    private String location;
    private String website;
    private String picture;
    private String email;

    private Collection<User> followers;
    private Collection<User> following;
    private Collection<Kweet> postedKweets;
    private Collection<Kweet> likedKweets;
    //endregion

    //region Properties
    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBiography() {
        return this.biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<User> getFollowers() {
        return this.followers;
    }

    public Collection<User> getFollowing() {
        return this.following;
    }

    public Collection<Kweet> getPostedKweets() {
        return this.postedKweets;
    }

    public Collection<Kweet> getLikedKweets() {
        return this.likedKweets;
    }
    //endregion

    //region Constructors
    public User(String userName, String password, String biography, String location, String website, String picture, String email) {
        this.userName = userName;
        this.password = password;
        this.biography = biography;
        this.location = location;
        this.website = website;
        this.picture = picture;
        this.email = email;

        this.followers = new ArrayList<User>();
        this.following = new ArrayList<User>();
        this.postedKweets = new ArrayList<Kweet>();
        this.likedKweets = new ArrayList<Kweet>();
    }
    //endregion

    //region Methods

    /**
     * Follow this user.
     * @param followedBy
     */
    public void follow(User followedBy){

        followedBy.following.add(this);
        this.followers.add(followedBy);

    }

    /**
     * Post a kweet.
     * @param kweet
     */
    public void postKweet(Kweet kweet){

        this.postedKweets.add(kweet);

    }
    //endregion

}
