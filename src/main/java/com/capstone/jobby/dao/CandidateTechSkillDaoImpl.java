package com.capstone.jobby.dao;

import com.capstone.jobby.model.CandidateTechSkill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CandidateTechSkillDaoImpl implements CandidateTechSkillDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<CandidateTechSkill> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<CandidateSkill> candidateSkills = session.createCriteria(CandidateSkill.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<CandidateTechSkill> criteria = builder.createQuery(CandidateTechSkill.class);

        // Specify criteria root
        criteria.from(CandidateTechSkill.class);

        // Execute query
        List<CandidateTechSkill> candidateTechSkills = session.createQuery(criteria).getResultList();

        session.close();
        return candidateTechSkills;
    }

    @Override
    public CandidateTechSkill findById(Long id) {
        Session session = sessionFactory.openSession();
        CandidateTechSkill candidateSkill = session.get(CandidateTechSkill.class,id);
        session.close();
        return candidateSkill;
    }

    @Override
    public void save(CandidateTechSkill candidateTechSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(candidateTechSkill);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(CandidateTechSkill candidateTechSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(candidateTechSkill);
        session.getTransaction().commit();
        session.close();
    }
}
