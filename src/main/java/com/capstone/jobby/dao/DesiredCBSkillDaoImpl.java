package com.capstone.jobby.dao;

import com.capstone.jobby.model.DesiredCBSkill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class DesiredCBSkillDaoImpl implements DesiredCBSkillDao{
    
    @Autowired
    private SessionFactory sessionFactory;

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
