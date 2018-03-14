package com.capstone.jobby.service;

import com.capstone.jobby.model.CandidateSkill;

import java.util.List;

public interface CandidateSkillService {
    List<CandidateSkill> findAll();
    CandidateSkill findById(Long id);
    void save(CandidateSkill candidateSkill);
    void delete(CandidateSkill candidateSkill);
}
