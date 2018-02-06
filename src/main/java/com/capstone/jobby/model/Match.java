package com.capstone.jobby.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

public class Match {
    @ManyToOne
    @JoinColumn(Candidate)
    private Candidate candidate;
}
