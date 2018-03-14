package com.capstone.jobby.service;

import com.capstone.jobby.model.ProfilePic;
import org.springframework.web.multipart.MultipartFile;

public interface ProfilePicService {
    ProfilePic findById(Long id);
    void save(ProfilePic resume, MultipartFile file);
    void delete(ProfilePic resume);
}
