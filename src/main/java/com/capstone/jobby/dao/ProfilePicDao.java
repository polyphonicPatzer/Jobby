package com.capstone.jobby.dao;

import com.capstone.jobby.model.ProfilePic;

import java.util.List;

public interface ProfilePicDao {
    List<ProfilePic> findAll();
    ProfilePic findById(Long id);
    void save(ProfilePic profilePic);
    void delete(ProfilePic profilePic);
}
