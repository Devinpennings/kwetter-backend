package model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@MappedSuperclass
public abstract class Model implements Comparable<Model> {

    //region Fields
    @Id
    private String id;

    private Timestamp createdAt;
    private Timestamp updatedAt;
    //endregion

    @PrePersist
    protected void onCreate(){
        this.id = UUID.randomUUID().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.updatedAt = timestamp;
        this.createdAt = timestamp;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Timestamp(System.currentTimeMillis());
    }

    //region Properties
    public String getId() { return this.id; }

    public void setId(String id) { this.id = id; }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int compareTo(Model o) {
        return this.createdAt.compareTo(o.createdAt);
    }

    //endregion

}
