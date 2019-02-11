package model;

public class Trend extends Tag {

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Trend(int characterLocation, String message) {
        super(characterLocation);
        this.message = message;
    }

}
