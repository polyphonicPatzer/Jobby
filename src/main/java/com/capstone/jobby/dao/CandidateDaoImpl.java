package com.capstone.jobby.dao;

import com.capstone.jobby.model.Candidate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CandidateDaoImpl implements CandidateDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Candidate> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Candidate> candidates = session.createCriteria(Candidate.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Candidate> criteria = builder.createQuery(Candidate.class);

        // Specify criteria root
        criteria.from(Candidate.class);

        // Execute query
        List<Candidate> candidates = session.createQuery(criteria).getResultList();

        session.close();
        return candidates;
    }

    @Override
    public Candidate findById(Long id) {
        Session session = sessionFactory.openSession();
        Candidate candidate = session.get(Candidate.class,id);
        session.close();
        return candidate;
    }

    @Override
    public void save(Candidate candidate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(candidate);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Candidate candidate) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(candidate);
        session.getTransaction().commit();
        session.close();
    }
}
