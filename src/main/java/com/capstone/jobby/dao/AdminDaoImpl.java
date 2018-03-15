package com.capstone.jobby.dao;

import com.capstone.jobby.model.Admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements  AdminDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Admin findById(Long id) {
        Session session = sessionFactory.openSession();
        Admin admin = session.get(Admin.class,id);
        session.close();
        return admin;
    }

    @Override
    public Admin findByUsername(String email) {
        Session session = sessionFactory.openSession();
        Admin admin = session.byNaturalId(Admin.class)
                .using("email", email)
                .load();
        return admin;
    }
}
