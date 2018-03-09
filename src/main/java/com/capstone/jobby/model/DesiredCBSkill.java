package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class DesiredCBSkill implements Serializable {

    @ManyToOne
    @JoinColumn(name="candidateId")
    @Id
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name="skillId")
    @Id
    private Skill skill;

    @Column
    private int skillRating;

    @Column
    private int skillWeight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateSkill that = (CandidateSkill) o;
        return Objects.equals(candidate, that.candidate) &&
                Objects.equals(skill, that.skill);
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

    public int getSkillWeight() {
        return skillWeight;
    }

    public void setSkillWeight(int skillWeight) {
        this.skillWeight = skillWeight;
    }
}
