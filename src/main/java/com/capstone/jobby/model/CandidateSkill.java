package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CandidateSkill implements Serializable {
    @ManyToOne
    @JoinColumn(name="candidateId")
    @Id
    Candidate candidate;

    @ManyToOne
    @JoinColumn(name="skillId")
    @Id
    Skill skill;

    @Column
    private int skillRating;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateSkill that = (CandidateSkill) o;
        return Objects.equals(candidate, that.candidate) &&
                Objects.equals(skill, that.skill);
    }

    @Override
    public int hashCode() {

        return Objects.hash(candidate, skill);
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getSkillRating() {
        return skillRating;
    }

    public void setSkillRating(int skillRating) {
        this.skillRating = skillRating;
    }

}
