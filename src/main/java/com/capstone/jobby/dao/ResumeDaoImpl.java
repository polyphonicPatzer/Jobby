package com.capstone.jobby.dao;

import com.capstone.jobby.model.Resume;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ResumeDaoImpl implements ResumeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Resume findById(Long id) {
        Session session = sessionFactory.openSession();
        Resume resume = session.get(Resume.class,id);
        session.close();
        return resume;
    }

    @Override
    public void save(Resume resume) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(resume);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Resume resume) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(resume);
        session.getTransaction().commit();
        session.close();
    }
}
