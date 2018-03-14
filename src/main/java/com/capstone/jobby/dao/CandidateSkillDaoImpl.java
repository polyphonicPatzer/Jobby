package com.capstone.jobby.dao;

import com.capstone.jobby.model.CandidateSkill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CandidateSkillDaoImpl implements CandidateSkillDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<CandidateSkill> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<CandidateSkill> candidateSkills = session.createCriteria(CandidateSkill.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<CandidateSkill> criteria = builder.createQuery(CandidateSkill.class);

        // Specify criteria root
        criteria.from(CandidateSkill.class);

        // Execute query
        List<CandidateSkill> candidateSkills = session.createQuery(criteria).getResultList();

        session.close();
        return candidateSkills;
    }

    //@Override
    //public List<CandidateSkill> findAllByID(long id) {
    //   Session session = sessionFactory.openSession();
    //    List<CandidateSkill> candidateSkill = session.
    //}


    @Override
    public CandidateSkill findById(Long id) {
        Session session = sessionFactory.openSession();
        CandidateSkill candidateSkill = session.get(CandidateSkill.class,id);
        session.close();
        return candidateSkill;
    }

    @Override
    public void save(CandidateSkill candidateSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(candidateSkill);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(CandidateSkill candidateSkill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(candidateSkill);
        session.getTransaction().commit();
        session.close();
    }
}
