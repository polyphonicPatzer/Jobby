package com.capstone.jobby.service;

import com.capstone.jobby.model.CandidateSkill;
import com.capstone.jobby.model.CandidateSurveyResults;
import java.util.List;

public interface SurveyService {
    List<CandidateSkill> findAll();
    CandidateSkill findById(Long id);
    void save(CandidateSkill candidate);
    void delete(CandidateSkill candidate);
}
