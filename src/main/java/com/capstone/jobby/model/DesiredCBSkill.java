package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class DesiredCBSkill implements Serializable {

    @Id
    private Long jobID;

    @Id
    private Long skillID;

    @Column
    private int skillRating;

    @Column
    private int skillWeight;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DesiredCBSkill that = (DesiredCBSkill) o;
        return Objects.equals(jobID, that.jobID) &&
                Objects.equals(skillID, that.skillID);
    }

    public Long getJobID() {
        return jobID;
    }

    public void setJobID(Long companyID) {
        this.jobID = companyID;
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

    public int getSkillWeight() {
        return skillWeight;
    }

    public void setSkillWeight(int skillWeight) {
        this.skillWeight = skillWeight;
    }
}
