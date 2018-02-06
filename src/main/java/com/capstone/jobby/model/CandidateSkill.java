package com.capstone.jobby.model;

import javax.persistence.*;
import java.io.Serializable;

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
}
