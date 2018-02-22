package com.capstone.jobby.model;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Candidate {
    @Id
    @Column(name="candidateId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name="candidateName")
    private String Name;

    @NotNull
    @Column(name="candidateEmail")
    private String Email;

    @Column(name="candidateResume")
    private String Resume;

    @Column(name="candidatePass")
    private String Pass;

    public Candidate() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getResume() {
        return Resume;
    }

    public void setResume(String resume) {
        Resume = resume;
    }

    public String getPass() {
        return Pass;
    }

    public void setPass(String pass) {
        Pass = pass;
    }
}
