package main.java.hibernate;

import main.java.JDBC.DeveloperDAO;
import main.java.model.Developer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mala on 3/29/2017.
 */
public class HibernateDeveloperDAO implements DeveloperDAO{
    private static Logger LOGGER = LoggerFactory.getLogger(HibernateDeveloperDAO.class);
    private SessionFactory sessionFactory;

    public HibernateDeveloperDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


//    public List<Developer> getAll() {
//        try (Session session = sessionFactory.openSession()) {
//            return (List<Developer>) session.createQuery(" FROM Developer ").list();
//        }
//    }

    @Override
    public Developer getByID(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Developer.class, id);
        }
    }

    @Override
    public void save(Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            session.save(developer);
        }
    }

    @Override
    public void update(Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(developer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while updating developer,rollback", e);
            }
        }
    }

    @Override
    public void remove(Developer developer) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.delete(developer);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while removing developer,rollback", e);
            }
        }
    }
}
