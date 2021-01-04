package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS user(id BIGINT PRIMARY KEY NOT NULL" +
                    " AUTO_INCREMENT, name VARCHAR(40), lastName VARCHAR(40)," +
                    "age SMALLINT)";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
            } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS user";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Object user = session.load(User.class, id);
            if(user != null) {
                session.delete(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            return session.createCriteria(User.class).list();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM user").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction != null) transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
