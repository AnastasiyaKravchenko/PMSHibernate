package main.java.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Mala on 3/28/2017.
 */
@Entity
@Table(name = "developers")
public class Developer extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "join_date")
    private Date joiDate;

    @Column(name = "salary")
    private int salary;

    @OneToMany
    @JoinTable(
            name = "dev_skills",
            joinColumns =
            @JoinColumn(name = "developer_id"),
            inverseJoinColumns =
            @JoinColumn(name = "skills_id"))
    private List<Skill> skillList;


    public Developer() {
    }

    public Developer(String name, int age, String country, String city, Date joiDate, int salary) {
        this.name = name;
        this.age = age;
        this.country = country;
        this.city = city;
        this.joiDate = joiDate;
        this.salary = salary;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public Date getJoiDate() {
        return joiDate;
    }

    public void setJoiDate(Date joiDate) {
        this.joiDate = joiDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Developer{" +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", joiDate=" + joiDate +
                ", salary=" + salary +
                '}';
    }
}
