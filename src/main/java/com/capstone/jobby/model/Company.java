package com.capstone.jobby.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyID")
    private int id;

    @NotNull
    @Column(name="companyName")
    private String name;

    @NotNull
    @Column(name="city")
    private String city;

    @NotNull
    @Column(name="state")
    private String state;

    @Column(name="companyActivationStatus")
    private Boolean Activation_Status;

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
