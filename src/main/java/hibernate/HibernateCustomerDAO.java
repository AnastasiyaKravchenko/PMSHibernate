package main.java.hibernate;

import main.java.JDBC.CustomerDAO;
import main.java.model.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mala on 3/29/2017.
 */
public class HibernateCustomerDAO implements CustomerDAO {

    private static Logger LOGGER = LoggerFactory.getLogger(HibernateCustomerDAO.class);
    private SessionFactory sessionFactory;

    public HibernateCustomerDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    public List<Customer> getAll() {
//        try (Session session = sessionFactory.openSession()) {
//
//            return (List<Customer>) session.createQuery("FROM Customer").list();
//        }
//    }

    @Override
    public Customer getByID(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Customer.class, id);
        }
    }

    @Override
    public void save(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            session.save(customer);
        }
    }

    @Override
    public void update(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(customer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while updating customer,rollback", e);
            }
        }
    }

    @Override
    public void remove(Customer customer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.delete(customer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while removing customer,rollback", e);
            }
        }
    }
}
