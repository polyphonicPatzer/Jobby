package com.capstone.jobby.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="companyID")
    private int ID;

    @Column(name="companyName")
    private String Name;

    @Column(name="companyActivationStatus")
    private Boolean Activation_Status;

    @Column(name="companyPass")
    private String Pass;

    public Company() {}

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Boolean getActivation_Status() {
        return Activation_Status;
    }

    public void setActivation_Status(Boolean activation_Status) {
        Activation_Status = activation_Status;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String Pass) {
        this.Pass = Pass;
    }
}
