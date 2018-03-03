package com.capstone.jobby.service;

import com.capstone.jobby.model.Candidate;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CandidateService extends UserDetailsService {
    List<Candidate> findAll();
    Candidate findById(Long id);
    Candidate findByUsername(String email);
    void save(Candidate candidate);
    void delete(Candidate candidate);
}
