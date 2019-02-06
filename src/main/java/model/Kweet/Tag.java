package model.Kweet;


import model.Model;

public abstract class Tag extends Model {

    //region Fields
    private int characterLocation;
    //endregion

    //region Properties
    public int getCharacterLocation() {
        return this.characterLocation;
    }

    public void setCharacterLocation(int characterLocation) {
        this.characterLocation = characterLocation;
    }
    //endregion

    //region Constructors
    public Tag(int characterLocation) {
        this.characterLocation = characterLocation;
    }
    //endregion

}
