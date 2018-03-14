package com.capstone.jobby.service;

import com.capstone.jobby.dao.CandidateDao;
import com.capstone.jobby.model.Candidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public Candidate findByUsername(String email) { return candidateDao.findByUsername(email); }

    @Override
    public void save(Candidate candidate) {
        candidateDao.save(candidate);
    }

    @Override
    public void delete(Candidate candidate) {
        candidateDao.delete(candidate);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Load candidate from database (throw exception if candidate isn't found)
        Candidate candidate = candidateDao.findByUsername(email);
        if (candidate == null) {
            throw new UsernameNotFoundException("Candidate not found");
        }
        System.out.println("\n\n\nINSIDE LOADUSERBYUSERNAME AND WAS SUCCESSFUL!!!\n\n\n");
        //Return company object
        return candidate;
    }
}
