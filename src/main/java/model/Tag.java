package model;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Tag extends Model {

    //region Fields
    private int characterLocation;

    @ManyToOne
    private Kweet kweet;

    //endregion
    //region Properties

    public int getCharacterLocation() {
        return this.characterLocation;
    }
    public void setCharacterLocation(int characterLocation) {
        this.characterLocation = characterLocation;
    }

    public Kweet getKweet() {
        return this.kweet;
    }

    public void setKweet(Kweet kweet) {
        this.kweet = kweet;
    }



    //endregion
    //region Constructors
    public Tag() {
    }

    public Tag(int characterLocation) {
        this.characterLocation = characterLocation;
    }
    //endregion

}
