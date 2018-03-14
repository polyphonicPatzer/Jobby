package com.capstone.jobby.service;

import com.capstone.jobby.dao.ResumeDao;
import com.capstone.jobby.model.Resume;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ResumeServiceImpl implements ResumeService {
    @Autowired
    private ResumeDao resumeDao;

    @Override
    public Resume findById(Long id) { return resumeDao.findById(id); }

    @Override
    public void save(Resume resume, MultipartFile file) {
        try {
            resume.setBytes(file.getBytes());
            resumeDao.save(resume);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from uploaded file");
        }
    }

    @Override
    public void delete(Resume resume) { resumeDao.delete(resume); }
}
