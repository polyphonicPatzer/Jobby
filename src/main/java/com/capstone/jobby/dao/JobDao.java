package com.capstone.jobby.dao;

import com.capstone.jobby.model.Job;

import java.util.List;

public interface JobDao {
    List<Job> findAll();
    Job findById(Long id);
    Job findByName(String name);
    List<Job> findJobsByCompanyId(Long companyId);
    void save(Job job);
    void delete(Job job);
}
