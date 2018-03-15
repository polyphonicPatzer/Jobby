package com.capstone.jobby.service;

import com.capstone.jobby.model.Job;

import java.util.List;

public interface JobService {
    List<Job> findAll();
    Job findById(Long id);
    Job findByName(String name);
    List<Job> findJobsByCompanyId(Long companyId);
    void save(Job job);
    void delete(Job job);
}
