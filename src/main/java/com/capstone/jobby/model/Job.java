package com.capstone.jobby.model;

import javax.persistence.*;


public class Job {
    @Id
    @Column(name="jobId")
    private int Id;

    @ManyToOne
    @JoinColumn(name="companyID")
    private Company company;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
