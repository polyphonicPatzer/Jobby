package com.capstone.jobby.dao;

import com.capstone.jobby.model.CandidateTechSkill;

import java.util.List;

public interface CandidateTechSkillDao {
    List<CandidateTechSkill> findAll();
    List<CandidateTechSkill> findAllByID(Long id);
    CandidateTechSkill findById(Long id);
    void save(CandidateTechSkill candidateTechSkill);
    void delete(CandidateTechSkill candidateTechSkill);
}
