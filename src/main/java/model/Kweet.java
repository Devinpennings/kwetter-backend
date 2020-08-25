package model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "kweets")
public class Kweet extends Model {

    //region Fields
    private String message;

    @ManyToOne
    private User author;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope=User.class)
    @ManyToMany
    @JoinTable(name = "kweet_liker",
               joinColumns = @JoinColumn(name = "fk_kweet"),
               inverseJoinColumns = @JoinColumn(name = "fk_user"))
    private Set<User> likers;

    @OneToMany(mappedBy = "kweet")
    private Set<Mention> mentions;

    @OneToMany(mappedBy = "kweet")
    private Set<Trend> trends;
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
        this.mentions = new HashSet<>();
        this.trends = new HashSet<>();
        this.likers = new HashSet<>();
    }

    public Kweet(String message){
        this.message = message;
        this.mentions = new HashSet<>();
        this.trends = new HashSet<>();
        this.likers = new HashSet<>();
    }

    public Kweet(String message, User author) {
        this.message = message;
        this.author = author;
        this.mentions = new HashSet<>();
        this.trends = new HashSet<>();
        this.likers = new HashSet<>();
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
