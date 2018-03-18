package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Match implements Serializable, Comparable<Match> {
    @Id
    private Long candidateID;

    @Id
    private Long jobID;

    @Column
    private double percent;

    public Long getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(Long candidateID) {
        this.candidateID = candidateID;
    }

    public Long getJobID() {
        return jobID;
    }

    public void setJobID(Long jobID) {
        this.jobID = jobID;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Match match = (Match) o;
        return Objects.equals(candidateID, match.candidateID) &&
                Objects.equals(jobID, match.jobID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(candidateID, jobID);
    }

    @Override
    public int compareTo(Match thatMatch) {
        if (this == thatMatch) return 0;

        if (this.percent < thatMatch.getPercent()) return 1;
        else if (this.percent > thatMatch.getPercent()) return -1;
        else return 0;
    }
}
