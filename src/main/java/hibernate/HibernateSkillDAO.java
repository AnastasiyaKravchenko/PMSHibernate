package hibernate;

import JDBC.SkillDAO;
import model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created by Mala on 3/29/2017.
 */
public class HibernateSkillDAO implements SkillDAO {
    private static Logger LOGGER = LoggerFactory.getLogger(HibernateCompanyDAO.class);
    private SessionFactory sessionFactory;

    public HibernateSkillDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public List<Skill> getAll() {
        try (Session session = sessionFactory.openSession()) {

            return (List<Skill>) session.createQuery("FROM Skill").list();
        }
    }

    @Override
    public Skill getByID(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Skill.class, id);
        }
    }

    @Override
    public void save(Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            session.save(skill);
        }
    }

    @Override
    public void update(Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.update(skill);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while updating skill,rollback", e);
            }
        }
    }

    @Override
    public void remove(Skill skill) {
        try (Session session = sessionFactory.openSession()) {
            try {
                session.beginTransaction();
                session.delete(skill);
                session.getTransaction().commit();
            } catch (Exception e) {
                session.getTransaction().rollback();
                LOGGER.error("Exception occurred while removing skill,rollback", e);
            }
        }
    }
}
