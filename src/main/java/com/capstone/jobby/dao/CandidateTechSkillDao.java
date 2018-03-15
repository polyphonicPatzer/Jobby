package com.capstone.jobby.dao;

import com.capstone.jobby.model.CandidateTechSkill;

import java.util.List;

public interface CandidateTechSkillDao {
    List<CandidateTechSkill> findAll();
    CandidateTechSkill findById(Long id);
    void save(CandidateTechSkill candidateTechSkill);
    void delete(CandidateTechSkill candidateTechSkill);
}
