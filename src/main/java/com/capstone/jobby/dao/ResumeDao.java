package com.capstone.jobby.dao;

import com.capstone.jobby.model.Resume;

public interface ResumeDao {
    Resume findById(Long id);
    void save(Resume resume);
    void delete(Resume resume);
}
