package com.capstone.jobby.service;

import com.capstone.jobby.dao.CandidateSkillDao;
import com.capstone.jobby.model.CandidateSkill;
import com.capstone.jobby.model.CandidateSurveyResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyServiceImpl implements SurveyService{
        @Autowired
        private CandidateSkillDao candidateSkillDao;

        @Override
        public List<CandidateSkill> findAll() {
            return candidateSkillDao.findAll();
        }

        @Override
        public CandidateSkill findById(Long id) {
            return candidateSkillDao.findById(id);
        }

        @Override
        public void save(CandidateSkill surveyResults) {
            candidateSkillDao.save(surveyResults);
        }

        @Override
        public void delete(CandidateSkill surveyResults) {
            candidateSkillDao.delete(surveyResults);
        }
}
