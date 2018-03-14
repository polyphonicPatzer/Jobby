package com.capstone.jobby.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Job {
    @Id
    @Column(name="id")
    private int id;

    @Column(name="companyID")
    private Long companyID;

    @NotNull
    private String name;

    @Column
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCompanyID() {
        return companyID;
    }

    public void setCompanyID(Long companyID) {
        this.companyID = companyID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
