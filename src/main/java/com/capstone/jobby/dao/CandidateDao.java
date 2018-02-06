package com.capstone.jobby.dao;

import com.capstone.jobby.model.Candidate;
import java.util.List;


public interface CandidateDao {
    List<Candidate> findAll();
    Candidate findById(Long id);
    void save(Candidate candidate);
    void delete(Candidate candidate);
}
