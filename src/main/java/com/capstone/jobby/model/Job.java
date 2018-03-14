package com.capstone.jobby.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Job {
    @Id
    @Column(name="jobId")
    private int id;

    @ManyToOne
    @JoinColumn(name="companyID")
    private Company company;

    @NotNull
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
