package com.capstone.jobby.service;

import com.capstone.jobby.model.CandidateTechSkill;

import java.util.List;

public interface CandidateTechSkillService {
    List<CandidateTechSkill> findAll();
    List<CandidateTechSkill> findAllByID(Long id);
    CandidateTechSkill findById(Long id);
    void save(CandidateTechSkill candidateSkill);
    void delete(CandidateTechSkill candidateSkill);
}
