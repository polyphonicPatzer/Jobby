package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class CandidateSkill implements Serializable {
    @ManyToOne
    @JoinColumn(name="candidateId")
    @Id
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name="skillId")
    @Id
    private Skill skill;

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
}
