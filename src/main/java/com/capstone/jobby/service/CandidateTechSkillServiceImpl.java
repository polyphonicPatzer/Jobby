package com.capstone.jobby.service;

import com.capstone.jobby.dao.CandidateTechSkillDao;
import com.capstone.jobby.model.CandidateTechSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateTechSkillServiceImpl implements CandidateTechSkillService {
        @Autowired
        private CandidateTechSkillDao candidateTechSkillDao;

        @Override
        public List<CandidateTechSkill> findAll() {
            return candidateTechSkillDao.findAll();
        }

        @Override
        public List<CandidateTechSkill> findAllByID(Long id){ return candidateTechSkillDao.findAllByID(id);}

        @Override
        public CandidateTechSkill findById(Long id) {
            return candidateTechSkillDao.findById(id);
        }

        @Override
        public void save(CandidateTechSkill candidateTechSkill) {
            candidateTechSkillDao.save(candidateTechSkill);
        }

        @Override
        public void delete(CandidateTechSkill candidateTechSkill) {
            candidateTechSkillDao.delete(candidateTechSkill);
        }
}
