package model;

import java.util.ArrayList;
import java.util.Collection;

public class Kweet extends Model {

    //region Fields
    private String message;

    private Collection<User> likers;

    private Collection<Mention> mentions;

    private Collection<Trend>  trends;
    //endregion

    //region Properties
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<User> getLikers() { return this.likers; }

    public Collection<Mention> getMentions() { return this.mentions; }

    public Collection<Trend> getTrends() { return this.trends; }
    //endregion

    //region Constructors
    public Kweet(String message) {
        this.message = message;
        this.mentions = new ArrayList<>();
        this.trends = new ArrayList<>();
        this.likers = new ArrayList<User>();
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

    //endregion

}
