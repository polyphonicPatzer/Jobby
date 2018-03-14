package com.capstone.jobby.dao;

import com.capstone.jobby.model.Company;

import java.util.List;

public interface CompanyDao {
    List<Company> findAll();
    Company findById(Long id);
    Company findByUsername(String email);
    void save(Company company);
    void delete(Company company);
}
