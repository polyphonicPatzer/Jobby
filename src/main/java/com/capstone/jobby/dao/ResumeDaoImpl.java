package com.capstone.jobby.dao;

import com.capstone.jobby.model.Resume;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class ResumeDaoImpl implements ResumeDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Resume> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Resume> resumes = session.createCriteria(Resume.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Resume> criteria = builder.createQuery(Resume.class);

        // Specify criteria root
        criteria.from(Resume.class);

        // Execute query
        List<Resume> resumes = session.createQuery(criteria).getResultList();

        session.close();
        return resumes;
    }

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
