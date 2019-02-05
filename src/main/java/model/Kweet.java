package model;

public class Kweet extends Model {

    //region Fields
    private String message;
    //endregion

    //region Properties
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    //endregion

    //region Constructors
    public Kweet(String message) {
        this.message = message;
    }
    //endregion

}
