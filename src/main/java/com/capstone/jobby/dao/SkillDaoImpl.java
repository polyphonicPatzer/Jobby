package com.capstone.jobby.dao;

import com.capstone.jobby.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class SkillDaoImpl implements  SkillDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Skill> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Skill> skills = session.createCriteria(Skill.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Skill> criteria = builder.createQuery(Skill.class);

        // Specify criteria root
        criteria.from(Skill.class);

        // Execute query
        List<Skill> skills = session.createQuery(criteria).getResultList();

        session.close();
        return skills;
    }

    @Override
    public Skill findById(Long id) {
        Session session = sessionFactory.openSession();
        Skill reference = session.get(Skill.class,id);
        session.close();
        return reference;
    }

    @Override
    public void save(Skill skill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(skill);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Skill skill) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(skill);
        session.getTransaction().commit();
        session.close();
    }
}
