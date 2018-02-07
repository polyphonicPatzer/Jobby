package com.capstone.jobby.service;

import com.capstone.jobby.model.Company;

import java.util.List;

public interface CompanyService {
    List<Company> findAll();
    Company findById(Long id);
    void save(Company company);
    void delete(Company company);
}
