package com.capstone.jobby.service;

import com.capstone.jobby.model.Candidate;

import java.util.List;

public interface CandidateService {
    List<Candidate> findAll();
    Candidate findById(Long id);
    void save(Candidate candidate);
    void delete(Candidate candidate);
}
