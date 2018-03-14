package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(candidate, match.candidate) &&
                Objects.equals(job, match.job);
    }

    @Override
    public int hashCode() {

        return Objects.hash(candidate, job);
    }
}
