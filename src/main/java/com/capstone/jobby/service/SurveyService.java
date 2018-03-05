package com.capstone.jobby.service;

import com.capstone.jobby.model.CandidateSurveyResults;
import java.util.List;

public interface SurveyService {
    List<CandidateSurveyResults> findAll();
    CandidateSurveyResults findById(Long id);
    void save(CandidateSurveyResults candidate);
    void delete(CandidateSurveyResults candidate);
}
