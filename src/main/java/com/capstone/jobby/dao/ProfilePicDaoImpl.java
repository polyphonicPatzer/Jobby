package com.capstone.jobby.dao;

import com.capstone.jobby.model.ProfilePic;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class ProfilePicDaoImpl implements ProfilePicDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<ProfilePic> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<ProfilePic> profilePics = session.createCriteria(ProfilePic.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<ProfilePic> criteria = builder.createQuery(ProfilePic.class);

        // Specify criteria root
        criteria.from(ProfilePic.class);

        // Execute query
        List<ProfilePic> profilePics = session.createQuery(criteria).getResultList();

        session.close();
        return profilePics;
    }

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
