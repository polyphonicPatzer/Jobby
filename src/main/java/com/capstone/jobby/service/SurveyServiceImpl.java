package com.capstone.jobby.service;

import com.capstone.jobby.dao.CandidateSkillDao;
import com.capstone.jobby.model.CandidateSurveyResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService{
        @Autowired
        private CandidateSkillDao candidateSkillDao;

        @Override
        public List<CandidateSurveyResults> findAll() {
            return candidateSkillDao.findAll();
        }

        @Override
        public CandidateSurveyResults findById(Long id) {
            return candidateSkillDao.findById(id);
        }

        @Override
        public void save(CandidateSurveyResults surveyResults) {
            candidateSkillDao.save(surveyResults);
        }

        @Override
        public void delete(CandidateSurveyResults surveyResults) {
            candidateSkillDao.delete(surveyResults);
        }
}
