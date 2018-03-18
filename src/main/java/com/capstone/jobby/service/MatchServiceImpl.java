package com.capstone.jobby.service;

import com.capstone.jobby.dao.MatchDao;
import com.capstone.jobby.model.Match;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchDao matchDao;

    @Override
    public List<Match> findAll() {
        return matchDao.findAll();
    }

    @Override
    public Match findById(Long id) {
        return matchDao.findById(id);
    }

    @Override
    public List<Match> findByCandidateId(Long candidateId) { return matchDao.findByCandidateId(candidateId); }

    @Override
    public List<Match> findByCandidateIdOrdered(Long candidateId) { return matchDao.findByCandidateIdOrdered(candidateId); }

    @Override
    public List<Match> findByJobIdOrdered(Long jobId) { return matchDao.findByJobIdOrdered(jobId); }

    @Override
    public void save(Match match) {
        matchDao.save(match);
    }

    @Override
    public void delete(Match match) {
        matchDao.delete(match);
    }

    @Override
    public void deleteByJobId(Long jobId) { matchDao.deleteByJobId(jobId); }
}
