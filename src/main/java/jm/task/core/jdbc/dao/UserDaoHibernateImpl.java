package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }
    Session session = Util.getSesFac().openSession();


    @Override
    public void createUsersTable() {
        Transaction transaction;
        transaction = session.beginTransaction();
        session.createSQLQuery("CREATE TABLE IF NOT EXISTS user" +
                "(id mediumint not null auto_increment," +
                "name VARCHAR(50)," +
                "lastname VARCHAR(50)," +
                "age tinyint," +
                "PRIMARY KEY (id))").executeUpdate();
        transaction.commit();
    }

    @Override
    public void dropUsersTable() {
        Transaction transaction;
        transaction = session.beginTransaction();
        session.createSQLQuery("DROP TABLE IF EXISTS user");
        transaction.commit();

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction;
        transaction = session.beginTransaction();
        User user = new User();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction;
        transaction = session.beginTransaction();
        User user = session.load(User.class, id);
        session.delete(user);
        transaction.commit();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        allUsers = session.createQuery("from User",User.class).list();
        session.getTransaction().commit();

        return allUsers;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction;
        transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
    }
}
