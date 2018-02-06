package com.capstone.jobby.dao;

import com.capstone.jobby.model.Reference;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class ReferenceDaoImpl implements ReferenceDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Reference> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Reference> references = session.createCriteria(Reference.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Reference> criteria = builder.createQuery(Reference.class);

        // Specify criteria root
        criteria.from(Reference.class);

        // Execute query
        List<Reference> references = session.createQuery(criteria).getResultList();

        session.close();
        return references;
    }

    @Override
    public Reference findById(Long id) {
        Session session = sessionFactory.openSession();
        Reference reference = session.get(Reference.class,id);
        session.close();
        return reference;
    }

    @Override
    public void save(Reference reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(reference);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Reference reference) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(reference);
        session.getTransaction().commit();
        session.close();
    }
}
