package com.capstone.jobby.dao;

import com.capstone.jobby.model.Job;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class JobDaoImpl implements JobDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Job> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Job> jobs = session.createCriteria(Job.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Job> criteria = builder.createQuery(Job.class);

        // Specify criteria root
        criteria.from(Job.class);

        // Execute query
        List<Job> jobs = session.createQuery(criteria).getResultList();

        session.close();
        return jobs;
    }

    @Override
    public Job findByName(String name) {
        Session session = sessionFactory.openSession();
        Job job = session.get(Job.class,name);
        session.close();
        return job;
    }

    @Override
    public void save(Job job) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(job);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Job job) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(job);
        session.getTransaction().commit();
        session.close();
    }
}
