package com.capstone.jobby.service;

import com.capstone.jobby.dao.JobDao;
import com.capstone.jobby.model.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements  JobService {
    @Autowired
    private JobDao jobDao;

    @Override
    public List<Job> findAll() {
        return jobDao.findAll();
    }

    @Override
    public Job findById(Long id) {
        return jobDao.findById(id);
    }

    @Override
    public void save(Job job) {
        jobDao.save(job);
    }

    @Override
    public void delete(Job job) {
        jobDao.delete(job);
    }
}
