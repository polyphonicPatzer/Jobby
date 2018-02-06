package com.capstone.jobby.dao;

import com.capstone.jobby.model.CandidateSkill;

import java.util.List;

public interface CandidateSkillDao {
    List<CandidateSkill> findAll();
    CandidateSkill findById(Long id);
    void save(CandidateSkill candidateSkill);
    void delete(CandidateSkill candidateSkill);
}
