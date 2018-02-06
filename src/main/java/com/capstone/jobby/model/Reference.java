package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Reference implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reference reference = (Reference) o;
        return Objects.equals(candidate, reference.candidate) &&
                Objects.equals(Phone, reference.Phone) &&
                Objects.equals(Name, reference.Name) &&
                Objects.equals(Email, reference.Email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(candidate, Phone, Name, Email);
    }
}
