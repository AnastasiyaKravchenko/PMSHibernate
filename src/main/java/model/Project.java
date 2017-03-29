package main.java.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mala on 3/28/2017.
 */
@Entity
@Table(name = "projects")
public class Project extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "cost")
    private int cost;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "dev_projects",
            joinColumns =
            @JoinColumn(name = "project_id"),
            inverseJoinColumns =
            @JoinColumn(name = "developer_id"))
    private List<Developer> developerList;

    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;

    public Project() {
    }

    public Project(String name, String description, int cost) {
        this.name = name;
        this.description = description;
        this.cost = cost;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Project{" +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                '}';
    }
}
