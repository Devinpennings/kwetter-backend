package model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Mention extends Tag {

    //region Fields
    @ManyToOne
    private User mentioned;

    //endregion
    //region Properties

    public User getMentioned() {
        return this.mentioned;
    }
    public void setMentioned(User mentioned) {
        this.mentioned = mentioned;
    }

    //endregion
    //region Constructors
    public Mention() {
        super();
    }

    public Mention(int characterLocation, User mentioned) {
        super(characterLocation);
        this.mentioned = mentioned;
    }
    //endregion


}
