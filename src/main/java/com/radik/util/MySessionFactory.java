package com.radik.util;

import com.radik.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;


public class MySessionFactory {

        private static final SessionFactory sessionFactory;

        static {
            try {
                ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

                Properties properties = new Properties();
                properties.load(classLoader.getResourceAsStream("hibernate.properties"));


                sessionFactory = new Configuration().addProperties(properties)
                        .addAnnotatedClass(Country.class)
                        .addAnnotatedClass(City.class)
                        .addAnnotatedClass(Address.class)
                        .addAnnotatedClass(Store.class)
                        .addAnnotatedClass(Staff.class)
                        .addAnnotatedClass(Customer.class)
                        .addAnnotatedClass(Language.class)
                        .addAnnotatedClass(Film.class)
                        .addAnnotatedClass(Category.class)
                        .addAnnotatedClass(FilmText.class)
                        .addAnnotatedClass(Actor.class)
                        .addAnnotatedClass(Inventory.class)
                        .addAnnotatedClass(Rental.class)
                        .addAnnotatedClass(Payment.class)
                        .buildSessionFactory();

            } catch (Throwable err) {
                System.err.println("Инициализация SessionFactory не удалась: " + err);
                throw new ExceptionInInitializerError(err);
            }
        }

        public static SessionFactory getSessionFactory() {
            return sessionFactory;
        }

        public static void shutdown() {
            getSessionFactory().close();
        }
}
