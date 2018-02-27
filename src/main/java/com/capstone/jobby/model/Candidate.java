package com.capstone.jobby.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Candidate {
    @Id
    @Column(name="candidateId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name="candidateName")
    private String name;

    @NotNull
    @Size(min = 5, max = 30)
    @Column(name="candidateEmail")
    private String email;

    @OneToOne
    @JoinColumn(name="resumeId")
    private Resume resume;

    @NotNull
    @Size(min = 6, max = 15)
    @Column(name="candidatePass")
    private String pass;

    public Candidate() {}

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
