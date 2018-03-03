package com.capstone.jobby.service;

import com.capstone.jobby.model.Company;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface CompanyService extends UserDetailsService {
    List<Company> findAll();
    Company findById(Long id);
    Company findByUsername(String email);
    void save(Company company);
    void delete(Company company);
}
