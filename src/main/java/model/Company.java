package main.java.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Mala on 3/28/2017.
 */
@Entity
@Table(name = "companies")
public class Company extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;


    public Company() {
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "project_company",
            joinColumns =
            @JoinColumn(name = "company_id"),
            inverseJoinColumns =
            @JoinColumn(name = "project_id"))
    private List<Project> projectList;


    public Company(String name, String address, String country, String city) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.city = city;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Company{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
