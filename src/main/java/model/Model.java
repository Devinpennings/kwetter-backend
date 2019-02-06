package model;

import java.sql.Time;
import java.sql.Timestamp;

public abstract class Model {

    //region Fields
    private long id;

    //TODO: Timestamps should be updated with JPA @nnotations.
    private Timestamp createdAt;
    private Timestamp updatedAt;
    //endregion

    //region Properties
    public long getId() {
        return this.id;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }
    //endregion

}