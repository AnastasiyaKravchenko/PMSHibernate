package main.java.hibernate;

import main.java.JDBC.ProjectDAO;
import main.java.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * Created by Mala on 3/29/2017.
 */
public class HibernateProjectDAO implements ProjectDAO {

    private static Logger LOGGER = LoggerFactory.getLogger(HibernateProjectDAO.class);
    private SessionFactory sessionFactory;

    public HibernateProjectDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Project getByID(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Project.class, id);
        }
    }

    @Override
    public void save(Project project) {
        try (Session session = sessionFactory.openSession()) {
            session.save(project);
        }
    }


    @Override
    public void update(Project project) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(project);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while updating project,rollback", e);
            }
        }
    }

    @Override
    public void remove(Project project) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.remove(project);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while removing developer,rollback", e);
            }
        }
    }
}
