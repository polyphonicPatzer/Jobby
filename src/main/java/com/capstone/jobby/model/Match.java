package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Match implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name="candidateId")
    private Candidate candidate;

    @Id
    @ManyToOne
    @JoinColumn(name="jobId")
    private Job job;

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Match() {}

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
