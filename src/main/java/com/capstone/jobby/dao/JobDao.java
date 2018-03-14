package com.capstone.jobby.dao;

import com.capstone.jobby.model.Job;

import java.util.List;

public interface JobDao {
    List<Job> findAll();
    Job findById(Long id);
    void save(Job job);
    void delete(Job job);
}
