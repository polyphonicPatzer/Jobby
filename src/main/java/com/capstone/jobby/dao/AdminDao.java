package com.capstone.jobby.dao;

import com.capstone.jobby.model.Admin;

public interface AdminDao {
    Admin findById(Long id);
    Admin findByUsername(String email);
}
