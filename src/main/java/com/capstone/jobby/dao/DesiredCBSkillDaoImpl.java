package com.capstone.jobby.dao;

import com.capstone.jobby.model.DesiredCBSkill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class DesiredCBSkillDaoImpl implements DesiredCBSkillDao{
    
    @Autowired
    private SessionFactory sessionFactory;

    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<DesiredCBSkill> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<CandidateSkill> candidateSkills = session.createCriteria(CandidateSkill.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<DesiredCBSkill> criteria = builder.createQuery(DesiredCBSkill.class);

        // Specify criteria root
        criteria.from(DesiredCBSkill.class);

        // Execute query
        List<DesiredCBSkill> desiredCBSkills = session.createQuery(criteria).getResultList();

        session.close();
        return desiredCBSkills;
    }

    @Override
    @Transactional
    public List<DesiredCBSkill> findAllById(Long id) {

        CriteriaQuery<DesiredCBSkill> c = em.getCriteriaBuilder().createQuery(DesiredCBSkill.class);
        Root<DesiredCBSkill> from = c.from(DesiredCBSkill.class);

        c.select(from);
        c.where(em.getCriteriaBuilder().equal(from.get("jobID"),Long.toString(id))); // <- this will add the restriction.

        c.orderBy(em.getCriteriaBuilder().asc(from.get("skillID")));
        return em.createQuery(c).getResultList();

    }

    @Override
    public DesiredCBSkill findById(Long id) {
        Session session = sessionFactory.openSession();
        DesiredCBSkill desiredCBSkill = session.get(DesiredCBSkill.class,id);
        session.close();
        return desiredCBSkill;
    }

    @Override
    public void save(DesiredCBSkill desiredCBSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(desiredCBSkill);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(DesiredCBSkill desiredCBSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(desiredCBSkill);
        session.getTransaction().commit();
        session.close();
    }
}
