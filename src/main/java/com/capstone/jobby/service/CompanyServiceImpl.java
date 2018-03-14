package com.capstone.jobby.service;

import com.capstone.jobby.dao.CompanyDao;
import com.capstone.jobby.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public Company findByUsername(String email) { return companyDao.findByUsername(email); }

    @Override
    public void save(Company company) {
        companyDao.save(company);
    }

    @Override
    public void delete(Company company) {
        companyDao.delete(company);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Load company from database (throw exception if company isn't found)
        Company company = companyDao.findByUsername(email);
        if (company == null) {
            throw new UsernameNotFoundException("Company not found");
        }
        System.out.println("\n\n\nINSIDE LOADUSERBYUSERNAME AND WAS SUCCESSFUL!!!\n\n\n");
        //Return company object
        return company;
    }
}
