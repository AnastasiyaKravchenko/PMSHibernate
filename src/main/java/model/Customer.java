package main.java.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mala on 3/28/2017.
 */
@Entity
@Table(name = "customers")
public class Customer extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private int inn;

    @Column(name = "edrpou")
    private int edrpou;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JoinTable(
            name = "project_customer",
            joinColumns = @JoinColumn(name = "customers_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private List<Project> projectList = new ArrayList<>();

    public Customer() {
    }

    public Customer(String name, int inn, int edrpou) {
        this.name = name;
        this.inn = inn;
        this.edrpou = edrpou;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public int getEdrpou() {
        return edrpou;
    }

    public void setEdrpou(int edrpou) {
        this.edrpou = edrpou;
    }

    @Override
    public String toString() {
        return "Customer{" +
                ", name='" + name + '\'' +
                ", inn=" + inn +
                ", edrpou=" + edrpou +
                '}';
    }
}
