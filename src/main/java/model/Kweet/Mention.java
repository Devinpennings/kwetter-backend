package model.Kweet;

import model.User.User;

public class Mention extends Tag {

    //region Fields
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
    public Mention(int characterLocation, User mentioned) {
        super(characterLocation);
        this.mentioned = mentioned;
    }
    //endregion


}
