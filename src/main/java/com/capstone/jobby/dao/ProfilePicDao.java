package com.capstone.jobby.dao;

import com.capstone.jobby.model.ProfilePic;

public interface ProfilePicDao {
    ProfilePic findById(Long id);
    void save(ProfilePic profilePic);
    void delete(ProfilePic profilePic);
}
