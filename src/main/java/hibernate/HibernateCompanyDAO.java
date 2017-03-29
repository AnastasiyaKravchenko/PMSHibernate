package main.java.hibernate;

import main.java.JDBC.CompanyDAO;
import main.java.model.Company;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mala on 3/29/2017.
 */
public class HibernateCompanyDAO implements CompanyDAO {

    private static Logger LOGGER = LoggerFactory.getLogger(HibernateCompanyDAO.class);
    private SessionFactory sessionFactory;

    public HibernateCompanyDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


//    public List<Company> getAll() {
//        try (Session session = sessionFactory.openSession()) {
//
//            return (List<Company>) session.createQuery("FROM Company").list();
//        }
//    }

    @Override
    public Company getByID(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Company.class, id);
        }
    }

    @Override
    public void save(Company company) {
        try (Session session = sessionFactory.openSession()) {
            session.save(company);
        }
    }

    @Override
    public void update(Company company) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(company);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while updating company,rollback", e);
            }
        }
    }

    @Override
    public void remove(Company company) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.delete(company);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while removing company,rollback", e);
            }
        }
    }
}
