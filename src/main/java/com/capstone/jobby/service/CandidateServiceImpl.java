package com.capstone.jobby.service;

import com.capstone.jobby.dao.CandidateDao;
import com.capstone.jobby.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateServiceImpl implements CandidateService {
    @Autowired
    private CandidateDao candidateDao;

    @Override
    public List<Candidate> findAll() {
        return candidateDao.findAll();
    }

    @Override
    public Candidate findById(Long id) {
        return candidateDao.findById(id);
    }

    @Override
    public void save(Candidate candidate) {
        candidateDao.save(candidate);
    }

    @Override
    public void delete(Candidate candidate) {
        candidateDao.delete(candidate);
    }
}
