package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.Service;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
  /*  CREATE TABLE `user_db`.`user` (
            `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
            `name` VARCHAR(45) NULL,
  `last_name` VARCHAR(45) NULL,
  `age` SMALLINT NULL,
    PRIMARY KEY (`id`),
    UNIQUE INDEX `id_UNIQUE` (`id` ASC) VISIBLE);
*/
    // реализуйте настройку соеденения с БД
    public static final String URL = "jdbc:mysql://localhost:3306/user_db";
    public static final String NAME = "root";
    public static final String PASSWORD = "134679852";
    public static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    public static Connection getConnection (){
      Connection connection = null;
    try {
      Class.forName(DRIVER);
      connection = DriverManager.getConnection(URL, NAME, PASSWORD);
    } catch (SQLException | ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }
  public static SessionFactory getSesFac(){
    Properties properties = new Properties();
    SessionFactory sessionFactory;
    Configuration configuration = new Configuration();

    properties.put(Environment.DRIVER,DRIVER);
    properties.put(Environment.URL,URL);
    properties.put(Environment.USER,NAME);
    properties.put(Environment.PASS,PASSWORD);

    configuration.setProperties(properties);
    configuration.addAnnotatedClass(User.class);
    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties()).build();
    sessionFactory = configuration.buildSessionFactory();

    return sessionFactory;

  }

}
