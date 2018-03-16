package com.capstone.jobby.dao;

import com.capstone.jobby.model.DesiredTechSkill;
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
public class DesiredTechSkillDaoImpl implements DesiredTechSkillDao{

    @Autowired
    private SessionFactory sessionFactory;
    @PersistenceContext
    EntityManager em;

    @Override
    @SuppressWarnings("unchecked")
    public List<DesiredTechSkill> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<CandidateSkill> candidateSkills = session.createCriteria(CandidateSkill.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<DesiredTechSkill> criteria = builder.createQuery(DesiredTechSkill.class);

        // Specify criteria root
        criteria.from(DesiredTechSkill.class);

        // Execute query
        List<DesiredTechSkill> desiredTechSkills = session.createQuery(criteria).getResultList();

        session.close();
        return desiredTechSkills;
    }

    @Override
    @Transactional
    public List<DesiredTechSkill> findAllByID(Long id) {

        CriteriaQuery<DesiredTechSkill> c = em.getCriteriaBuilder().createQuery(DesiredTechSkill.class);
        Root<DesiredTechSkill> from = c.from(DesiredTechSkill.class);

        c.select(from);
        c.where(em.getCriteriaBuilder().equal(from.get("jobID"),Long.toString(id)));

        c.orderBy(em.getCriteriaBuilder().asc(from.get("skillID")));
        return em.createQuery(c).getResultList();

    }

    @Override
    public DesiredTechSkill findById(Long id) {
        Session session = sessionFactory.openSession();
        DesiredTechSkill desiredTechSkill = session.get(DesiredTechSkill.class,id);
        session.close();
        return desiredTechSkill;
    }

    @Override
    public void save(DesiredTechSkill desiredTechSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(desiredTechSkill);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(DesiredTechSkill desiredTechSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(desiredTechSkill);
        session.getTransaction().commit();
        session.close();
    }
}
