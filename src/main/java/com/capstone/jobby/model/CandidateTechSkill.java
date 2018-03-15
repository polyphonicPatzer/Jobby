package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CandidateTechSkill implements Serializable {

    @Id
    private Long candidateID;

    @Id
    private Long skillID;

    @Column
    private int skillRating;


    public Long getCandidateID() {
        return candidateID;
    }

    public void setCandidateID(Long candidateID) {
        this.candidateID = candidateID;
    }

    public Long getSkillID() {
        return skillID;
    }

    public void setSkillID(Long skillID) {
        this.skillID = skillID;
    }

    public int getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(int skillRating) {
        this.skillRating = skillRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateTechSkill that = (CandidateTechSkill) o;
        return Objects.equals(candidateID, that.candidateID) &&
                Objects.equals(skillID, that.skillID);
    }

    @Override
    public int hashCode() {

        return Objects.hash(candidateID, skillID);
    }
}
