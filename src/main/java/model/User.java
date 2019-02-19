package model;

import com.fasterxml.jackson.annotation.*;

import java.util.ArrayList;
import java.util.Collection;

public class User extends Model {

    //region Fields
    private String username;

    @JsonIgnore
    private String password;
    private String biography;
    private String location;
    private String website;
    private String picture;
    private String email;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=User.class)
    private Collection<User> followers;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=User.class)
    private Collection<User> following;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Kweet.class)
    private Collection<Kweet> postedKweets;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=Kweet.class)
    private Collection<Kweet> likedKweets;
    //endregion

    //region Properties
    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() { return this.password; }

    public void setPassword(String password) { this.password = password; }

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
    public User(){
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.postedKweets = new ArrayList<>();
        this.likedKweets = new ArrayList<>();
    }

    public User(String username, String password, String biography, String location, String website, String picture, String email) {
        this.username = username;
        this.password = password;
        this.biography = biography;
        this.location = location;
        this.website = website;
        this.picture = picture;
        this.email = email;

        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.postedKweets = new ArrayList<>();
        this.likedKweets = new ArrayList<>();
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

    public void unfollow(User follower) {

        follower.following.remove(this);
        this.followers.remove(follower);

    }

    /**
     * Post a kweet.
     * @param kweet
     */
    public void postKweet(Kweet kweet){

        this.postedKweets.add(kweet);
        kweet.setAuthor(this);

    }
    //endregion

}
