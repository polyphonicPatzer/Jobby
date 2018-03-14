package com.capstone.jobby.dao;

import com.capstone.jobby.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class CompanyDaoImpl implements CompanyDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @SuppressWarnings("unchecked")
    public List<Company> findAll() {
        Session session = sessionFactory.openSession();

        // DEPRECATED as of Hibernate 5.2.0
        // List<Company> companies = session.createCriteria(Company.class).list();

        // Create CriteriaBuilder
        CriteriaBuilder builder = session.getCriteriaBuilder();

        // Create CriteriaQuery
        CriteriaQuery<Company> criteria = builder.createQuery(Company.class);

        // Specify criteria root
        criteria.from(Company.class);

        // Execute query
        List<Company> companies = session.createQuery(criteria).getResultList();

        session.close();
        return companies;
    }

    @Override
    public Company findById(Long id) {
        System.out.println("\n\n\n\nINSIDE FIND BY ID!! \n\n\n\n");
        Session session = sessionFactory.openSession();
        Company company = session.get(Company.class,id);
        session.close();
        return company;
    }

    @Override
    public Company findByUsername(String email) {
        System.out.println("\n\n\n\nINSIDE FIND USERNAME!! \n\n\n\n");
        Session session = sessionFactory.openSession();
        Company company = session.byNaturalId(Company.class)
                .using("email", email)
                .load();
        return company;
    }

    @Override
    public void save(Company company) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(company);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void delete(Company company) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(company);
        session.getTransaction().commit();
        session.close();
    }
}
