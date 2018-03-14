package com.capstone.jobby.service;

import com.capstone.jobby.model.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job findById(Long id);
    void save(Job job);
    void delete(Job job);
}
