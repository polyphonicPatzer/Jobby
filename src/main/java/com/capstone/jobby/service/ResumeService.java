package com.capstone.jobby.service;

import com.capstone.jobby.model.Resume;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ResumeService {
    List<Resume> findAll();
    Resume findById(Long id);
    void save(Resume resume, MultipartFile file);
    void delete(Resume resume);
}
