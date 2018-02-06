package com.capstone.jobby.model;

import javax.persistence.*;

@Entity
public class Reference {
    @ManyToOne
    @Id
    @JoinColumn(name="candidateId")
    private Candidate candidate;

    @Column(name="referencePhone")
    @Id
    private Long Phone;

    @Column(name="referenceName")
    private String Name;

    @Column(name="referencEmail")
    private String Email;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Long getPhone() {
        return Phone;
    }

    public void setPhone(Long phone) {
        Phone = phone;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}
