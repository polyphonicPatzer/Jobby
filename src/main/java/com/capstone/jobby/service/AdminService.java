package com.capstone.jobby.service;

import com.capstone.jobby.model.Admin;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminService extends UserDetailsService {
    Admin findById(Long id);
    Admin findByUsername(String email);
}
