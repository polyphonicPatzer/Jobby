package com.capstone.jobby.dao;

import com.capstone.jobby.model.Resume;

import java.util.List;

public interface ResumeDao {
    List<Resume> findAll();
    Resume findById(Long id);
    void save(Resume resume);
    void delete(Resume resume);
}
