package model.Kweet;

import model.Model;
import model.User.User;

import java.util.ArrayList;
import java.util.Collection;

public class Kweet extends Model {

    //region Fields
    private String message;

    private Collection<User> likers;
    //endregion

    //region Properties
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Collection<User> getLikers() { return this.likers; }

    //endregion

    //region Constructors
    public Kweet(String message) {
        this.message = message;
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

    //enregion

}
