package com.capstone.jobby.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyID")
    private int id;

    @NotNull
    @Size(min = 3, max = 40)
    @Column(name="companyName")
    private String name;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name="companyEmail")
    private String email;

    @NotNull
    @Size(min = 3, max = 30)
    @Column(name="city")
    private String city;

    @NotNull
    @Size(min = 3, max = 20)
    @Column(name="state")
    private String state;

    @Column(name="companyActivationStatus")
    private Boolean Activation_Status;

    @NotNull
    @Size(min = 6, max = 15)
    @Column(name="companyPass")
    private String pass;

    public Company() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCity() { return city; }

    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }

    public void setState(String state) { this.state = state; }

    public Boolean getActivation_Status() {
        return Activation_Status;
    }

    public void setActivation_Status(Boolean activation_Status) {
        Activation_Status = activation_Status;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String Pass) {
        this.pass = Pass;
    }
}
