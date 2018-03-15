package com.capstone.jobby.service;

import com.capstone.jobby.dao.AdminDao;
import com.capstone.jobby.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminDao adminDao;

    @Override
    public Admin findById(Long id) { return adminDao.findById(id); }

    @Override
    public Admin findByUsername(String email) { return adminDao.findByUsername(email); }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Load admin from database (throw exception if admin isn't found)
        Admin admin = adminDao.findByUsername(email);
        if (admin == null) {
            throw new UsernameNotFoundException("Admin not found");
        }
        System.out.println("\n\n\nINSIDE LOADUSERBYUSERNAME AND WAS SUCCESSFUL!!!\n\n\n");
        //Return admin object
        return admin;
    }
}
