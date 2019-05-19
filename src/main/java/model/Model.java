package model;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
        if (this.id == null) this.id = UUID.randomUUID().toString();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (this.createdAt == null)
            this.createdAt = timestamp;
        this.updatedAt = timestamp;
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

    @JsonIgnore
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @JsonIgnore
    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Model)
            if(((Model) o).getId() != null && this.id != null)
                return this.id.equals(((Model) o).getId());

        return this.hashCode() == o.hashCode();
    }

    @Override
    public int compareTo(Model o) {

        if (this.createdAt != null && o.getCreatedAt() != null){
            int date = Integer.compare(this.createdAt.getNanos(), o.createdAt.getNanos());
            if (date != 0) return 1;
        }

        if (this.id != null && o.getId() != null) {
            return this.id.compareTo(o.getId());
        }

        return Integer.compare(this.hashCode(), o.hashCode());


    }

    //endregion

}
