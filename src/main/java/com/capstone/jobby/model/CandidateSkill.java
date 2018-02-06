package com.capstone.jobby.model;

import javax.persistence.*;

@Entity
public class CandidateSkill {
    @ManyToOne
    @JoinColumn(name="candidateId")
    @Id
    private Candidate candidate;

    @OneToOne
    @JoinColumn(name="skillId")
    @Id
    private Skill skill;
}
