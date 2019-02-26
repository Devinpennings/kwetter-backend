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
    public void onCreate(){
        this.id = UUID.randomUUID().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        this.updatedAt = timestamp;
        this.createdAt = timestamp;
    }

    @PreUpdate
    public void onUpdate(){
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

        if (this.createdAt != null && o.getCreatedAt() != null){
            int date = Integer.compare(this.createdAt.getNanos(), o.createdAt.getNanos());
            if (date != 0) return date;
        }

        if (this.id != null && o.getId() != null) {
            return this.id.compareTo(o.getId());
        }

        return Integer.compare(this.hashCode(), o.hashCode());


    }

    //endregion

}
