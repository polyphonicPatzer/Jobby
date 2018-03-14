package com.capstone.jobby.dao;

import com.capstone.jobby.model.ProfilePic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProfilePicDaoImpl implements ProfilePicDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public ProfilePic findById(Long id) {
        Session session = sessionFactory.openSession();
        ProfilePic profilePic = session.get(ProfilePic.class,id);
        session.close();
        return profilePic;
    }

    @Override
    public void save(ProfilePic profilePic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(profilePic);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(ProfilePic profilePic) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(profilePic);
        session.getTransaction().commit();
        session.close();
    }
}
