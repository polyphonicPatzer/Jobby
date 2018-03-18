package com.capstone.jobby.dao;

import com.capstone.jobby.model.Match;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class MatchDaoImpl implements MatchDao {
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @SuppressWarnings("unchecked")
    public List<Match> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Match> matches = session.createCriteria(Match.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Match> criteria = builder.createQuery(Match.class);

        // Specify criteria root
        criteria.from(Match.class);

        // Execute query
        List<Match> matches = session.createQuery(criteria).getResultList();

        session.close();
        return matches;
    }

    @Override
    public Match findById(Long id) {
        Session session = sessionFactory.openSession();
        Match gif = session.get(Match.class,id);
        session.close();
        return gif;
    }

    @Override
    public List<Match> findByCandidateId(Long candidateId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
        Root<Match> root = criteria.from(Match.class);
        ParameterExpression<Long> candidateIdParam = builder.parameter(Long.class);
        criteria.where(builder.equal(root.get("candidateID"), candidateIdParam));
        Query<Match> query = session.createQuery(criteria);
        query.setParameter(candidateIdParam, candidateId);
        return query.getResultList();
    }

    @Override
    public List<Match> findByCandidateIdOrdered(Long candidateId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
        Root<Match> root = criteria.from(Match.class);
        ParameterExpression<Long> candidateIdParam = builder.parameter(Long.class);
        criteria.where(builder.equal(root.get("candidateID"), candidateIdParam));
        criteria.orderBy(builder.desc(root.get("percent")));
        Query<Match> query = session.createQuery(criteria);
        query.setParameter(candidateIdParam, candidateId);
        return query.getResultList();
    }

    @Override
    public List<Match> findByJobIdOrdered(Long jobId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
        Root<Match> root = criteria.from(Match.class);
        ParameterExpression<Long> jobIdParam = builder.parameter(Long.class);
        criteria.where(builder.equal(root.get("jobID"), jobIdParam));
        criteria.orderBy(builder.desc(root.get("percent")));
        Query<Match> query = session.createQuery(criteria);
        query.setParameter(jobIdParam, jobId);
        return query.getResultList();
    }



    @Override
    public void save(Match match) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(match);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Match match) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(match);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void deleteByJobId(Long jobId) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
        Root<Match> root = criteria.from(Match.class);
        ParameterExpression<Long> jobIdParam = builder.parameter(Long.class);
        criteria.where(builder.equal(root.get("jobID"), jobIdParam));
        Query<Match> query = session.createQuery(criteria);
        query.setParameter(jobIdParam, jobId);
        List<Match> matches = query.getResultList();
        for (Match match : matches) {
            delete(match);
        }
    }
}
