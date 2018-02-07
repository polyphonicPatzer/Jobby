package com.capstone.jobby.service;

import com.capstone.jobby.dao.CompanyDao;
import com.capstone.jobby.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyDao companyDao;

    @Override
    public List<Company> findAll() {
        return companyDao.findAll();
    }

    @Override
    public Company findById(Long id) {
        return companyDao.findById(id);
    }

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    public void delete(Company company) {
        companyDao.delete(company);
    }
}
