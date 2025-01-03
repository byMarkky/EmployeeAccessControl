package org.example.control.dao;

import jakarta.persistence.TypedQuery;
import org.example.control.HibernateUtil;
import org.example.control.model.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyDao {

    private static final Logger log = LoggerFactory.getLogger(CompanyDao.class);

    public void create(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(company);
        log.info("COMPANY WITH CIF {} CREATED", company.getCif());

        tx.commit();
        session.close();
    }

    public void update(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();

        session.merge(company);
        log.info("COMPANY WITH CIF {} UPDATED", company.getCif());

        tx.commit();
        session.close();
    }

    public Company getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Company company = session.find(Company.class, id);
        if (company != null) log.info("COMPANY WITH ID {} FOUND", company.getId());
        else log.info("COMPANY WITH ID {} DO NOT EXISTS", id);

        tx.commit();
        session.close();

        return company;
    }

    public Company getByCif(String cif) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Company company = null;

        try {
            // Crear la query usando HQL
            String hql = "FROM Company c WHERE c.CIF = :cif";
            TypedQuery<Company> query = session.createQuery(hql, Company.class);
            query.setParameter("cif", cif);

            // Obtener el resultado único
            company = query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR SEARCHING COMPANY {}: {}", cif, e.getStackTrace());
        } finally {
            session.close();
        }

        if (company != null) log.info("COMPANY WITH CIF {} FOUND", company.getCif());
        else log.info("COMPANY WITH CIF {} DO NOT EXISTS", cif);

        return company;
    }

    public void remove(Company company) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.getTransaction();

        session.remove(company);
        log.info("COMPANY WITH CIF {} DELETED", company.getCif());

        tx.commit();
        session.close();
    }

}
