package main.java.model;

import javax.persistence.*;

/**
 * Created by Mala on 3/28/2017.
 */
@Entity
@Table(name = "skills")
public class Skill extends BaseEntity{

    @Column(name = "description")
    private String description;

    public Skill() {
    }

    public Skill(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Skill{" +
                ", description='" + description + '\'' +
                '}';
    }
}
