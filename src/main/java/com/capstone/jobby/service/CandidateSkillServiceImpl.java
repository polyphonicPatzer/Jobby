package com.capstone.jobby.service;

import com.capstone.jobby.dao.CandidateSkillDao;
import com.capstone.jobby.model.CandidateSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateSkillServiceImpl implements CandidateSkillService {
    @Autowired
    private CandidateSkillDao candidateSkillDao;

    @Override
    public List<CandidateSkill> findAll() {
        return candidateSkillDao.findAll();
    }

    @Override
    public List<CandidateSkill> findAllByID(Long id) {return candidateSkillDao.findAllByID(id);}

    @Override
    public CandidateSkill findById(Long id) {
        return candidateSkillDao.findById(id);
    }

    @Override
    public List<CandidateSkill> findSkillsByCandidateId(Long candidateId) { return candidateSkillDao.findSkillsByCandidateId(candidateId); }

    @Override
    public void save(CandidateSkill candidateSkill) {
        candidateSkillDao.save(candidateSkill);
    }

    @Override
    public void delete(CandidateSkill candidateSkill) {
        candidateSkillDao.delete(candidateSkill);
    }
}
