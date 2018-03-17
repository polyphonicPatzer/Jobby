package com.capstone.jobby.service;

import com.capstone.jobby.model.ProfilePic;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProfilePicService {
    List<ProfilePic> findAll();
    ProfilePic findById(Long id);
    void save(ProfilePic resume, MultipartFile file);
    void delete(ProfilePic resume);
}
