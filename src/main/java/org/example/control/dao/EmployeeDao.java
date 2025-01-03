package org.example.control.dao;

import jakarta.persistence.TypedQuery;
import org.example.control.HibernateUtil;
import org.example.control.model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class EmployeeDao {

    private static final Logger log = LoggerFactory.getLogger(EmployeeDao.class);

    public void create(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.persist(employee);
        log.info("EMPLOYEE {} CREATED", employee.getDni());

        tx.commit();
        session.close();
    }

    public void update(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.merge(employee);
        log.info("EMPLOYEE {} UPDATED", employee.getDni());

        tx.commit();
        session.close();
    }

    public Employee getById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Employee employee = session.find(Employee.class, id);
        if (employee != null) log.info("EMPLOYEE {} FOUND", employee.getId());
        else log.info("EMPLOYEE WITH ID {} DO NOT EXISTS", id);

        tx.commit();
        session.close();

        return employee;
    }

    public Employee getByDni(String dni) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Employee employee = null;

        try {
            // Crear la query usando HQL
            String hql = "FROM Employee e WHERE e.DNI = :dni";
            TypedQuery<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("dni", dni);

            // Obtener el resultado único
            employee = query.getSingleResult();
        } catch (Exception e) {
            log.error("ERROR SEARCHING COMPANY {}: {}", dni, e.getStackTrace());
        } finally {
            session.close();
        }

        if (employee != null) log.info("COMPANY WITH CIF {} FOUND", employee.getDni());
        else log.info("COMPANY WITH CIF {} DO NOT EXISTS", dni);

        return employee;
    }

    public List<Employee> getAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Employee> employees = null;

        employees = session.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();

        tx.commit();
        session.close();

        return employees;
    }

    public void remove(Employee employee) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        session.remove(employee);
        log.info("EPMLOYEE {} REMOVED", employee.getDni());

        tx.commit();
        session.close();
    }

}
