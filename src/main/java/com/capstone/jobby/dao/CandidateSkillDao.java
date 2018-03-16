package com.capstone.jobby.dao;

import com.capstone.jobby.model.CandidateSkill;
import com.capstone.jobby.model.CandidateSurveyResults;

import java.util.List;

public interface CandidateSkillDao {
    List<CandidateSkill> findAll();
    CandidateSkill findById(Long id);
    List<CandidateSkill> findSkillsByCandidateId(Long candidateId);
    void save(CandidateSkill candidateSkill);
    void delete(CandidateSkill candidateSkill);
}
