package com.capstone.jobby.service;

import com.capstone.jobby.dao.ProfilePicDao;
import com.capstone.jobby.model.ProfilePic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProfilePicServiceImpl implements ProfilePicService {
    @Autowired
    private ProfilePicDao profilePicDao;

    @Override
    public List<ProfilePic> findAll() { return profilePicDao.findAll(); }

    @Override
    public ProfilePic findById(Long id) { return profilePicDao.findById(id); }

    @Override
    public void save(ProfilePic profilePic, MultipartFile file) {
        try {
            profilePic.setBytes(file.getBytes());
            profilePicDao.save(profilePic);
        } catch (IOException e) {
            System.err.println("Unable to get byte array from uploaded file");
        }
    }

    @Override
    public void delete(ProfilePic profilePic) { profilePicDao.delete(profilePic); }
}
