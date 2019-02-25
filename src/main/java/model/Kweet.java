package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.Collection;

public class Kweet extends Model {

    //region Fields
    private String message;

    private User author;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=User.class)    private Collection<User> likers;
    private Collection<Mention> mentions;
    private Collection<Trend>  trends;
    //endregion

    //region Properties
    public String getMessage() {
        return this.message;
    }

    public User getAuthor() { return this.author; }

    public void setAuthor(User author) { this.author = author; }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<User> getLikers() { return this.likers; }

    public Collection<Mention> getMentions() { return this.mentions; }

    public Collection<Trend> getTrends() { return this.trends; }
    //endregion

    //region Constructors
    public Kweet(){
        this.mentions = new ArrayList<>();
        this.trends = new ArrayList<>();
        this.likers = new ArrayList<>();
    }

    public Kweet(String message){
        this.message = message;
        this.mentions = new ArrayList<>();
        this.trends = new ArrayList<>();
        this.likers = new ArrayList<>();
    }

    public Kweet(String message, User author) {
        this.message = message;
        this.author = author;
        this.mentions = new ArrayList<>();
        this.trends = new ArrayList<>();
        this.likers = new ArrayList<>();
    }
    //endregion

    //region Methods

    /**
     * Like this tweet.
     * @param u
     */
    public void like(User u){

        this.likers.add(u);
        u.getLikedKweets().add(this);

    }

    public void unLike(User u) {

        this.likers.remove(u);
        u.getLikedKweets().remove(this);

    }

    //endregion

}
