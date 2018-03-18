package com.capstone.jobby.dao;

import com.capstone.jobby.model.CandidateSkill;
import com.capstone.jobby.model.CandidateTechSkill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class CandidateTechSkillDaoImpl implements CandidateTechSkillDao {
    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    EntityManager em;

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
    @Transactional
    public List<CandidateTechSkill> findAllByID(Long id) {
        CriteriaQuery<CandidateTechSkill> c = em.getCriteriaBuilder().createQuery(CandidateTechSkill.class);
        Root<CandidateTechSkill> from = c.from(CandidateTechSkill.class);

        c.select(from);
        c.where(em.getCriteriaBuilder().equal(from.get("candidateID"),Long.toString(id))); // <- this will add the restriction.

        c.orderBy(em.getCriteriaBuilder().asc(from.get("skillID")));
        return em.createQuery(c).getResultList();

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
