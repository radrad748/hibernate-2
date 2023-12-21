package com.radik;

import com.radik.entity.*;
import com.radik.entity.enums.Rating;
import com.radik.entity.enums.SpecialFeatures;
import com.radik.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Runner {
    public static void main(String[] args) {
        try (Session session = MySessionFactory.getSessionFactory().openSession()){

        }
    }

    public static void addCustomer(Customer customer, Session session) {
        try {
            session.beginTransaction();
            session.persist(customer);
            session.getTransaction().commit();
        } catch (Throwable err) {
            session.getTransaction().rollback();
            session.close();
            throw err;
        }
    }
    public static boolean returnRental(Customer customer, Inventory inventory, Session session) {
        try {
            Query<Rental> query = session.createQuery("from Rental where customer = :person and inventory = :film", Rental.class);
            query.setParameter("person", customer);
            query.setParameter("film", inventory);
            Rental rental = query.getSingleResult();
            if (rental.getReturnDate() != null) return false;

            rental.setReturnDate(LocalDateTime.now());
            session.beginTransaction();
            session.merge(rental);
            session.getTransaction().commit();
            return true;
        } catch (Throwable err) {
            session.getTransaction().rollback();
            session.close();
            throw err;
        }
    }

    public static boolean rentFilm(Customer customer, Film film, Session session) {
        try {
            List<Inventory> listI = getListInventoryByIdCustomerAndFilm(customer, film, session);
            if (listI.isEmpty()) {
                createRentalWithInventoryNotExist(customer, film, session);
                return true;
            } else {
                List<Rental> listR = getListRentalByIdListInventory(listI, session);
                if (listR.isEmpty()) {
                    createRentalWithInventoryExist(customer, listI.get(0), session);
                    return true;
                } else {
                    for (Rental r : listR) {
                        if (r.getReturnDate() == null) {
                            return false;
                        }
                    }
                    createRentalWithInventoryExist(customer, listI.get(0), session);
                    return true;
                }
            }
        } catch (Throwable err) {
            session.getTransaction().rollback();
            session.close();
            throw err;
        }
    }
    public static void advertisementOfNewFilm(Session session) {
        try {
            Film film = new Film();
            film.setTitle("Wanted");
            film.setDescription("some description");
            film.setReleaseYear("2023");
            film.setRentalDuration(4);
            film.setRentalRate(new BigDecimal("3.55"));
            film.setLenght(90);
            film.setReplacementCost(new BigDecimal("20.11"));
            film.setRating(Rating.R);

            Language language = session.get(Language.class, 1);
            film.setLanguage(language);

            Actor actor = session.get(Actor.class, 1);
            film.addActor(actor);
            Actor actor1 = session.get(Actor.class, 2);
            film.addActor(actor1);
            Actor actor2 = session.get(Actor.class, 3);
            film.addActor(actor2);

            Category category = session.get(Category.class, 1);
            film.addCategory(category);

            FilmText text = new FilmText();
            text.setFilm(film);
            text.setTitle(film.getTitle());
            text.setDescription(film.getDescription());

            session.beginTransaction();
            session.persist(film);
            session.persist(text);
            session.getTransaction().commit();

            System.out.println("Вышел новый фильм: название - " + film.getTitle());
            System.out.println("год - " + film.getReleaseYear());
            System.out.println("Язык - " + film.getLanguage().getName());
            System.out.println("продолжительность - " + film.getLenght() + " минут");
            System.out.println("описание - " + film.getDescription());
            System.out.print("категория - ");
            for(Category c : film.getCategories()){
                System.out.print(c.getName() + " ");
            }
            System.out.println();
            System.out.print("актеры - ");
            for(Actor a : film.getActors()) {
                System.out.print(a.getFirstName() + " " + a.getLastName() + ", ");
            }

        } catch (Throwable err) {
            session.close();
            throw err;
        }
    }






    public static List<Inventory> getListInventoryByIdCustomerAndFilm(Customer customer, Film film, Session session) {
        try {
            Query<Inventory> queryInvetory = session.createQuery("from Inventory where film = :customerFilm and store = :customerStore", Inventory.class);
            queryInvetory.setParameter("customerFilm", film);
            queryInvetory.setParameter("customerStore", customer.getStore());
            List<Inventory> list = queryInvetory.list();

            return list;
        } catch (Throwable err) {
            session.close();
            throw err;
        }
    }

    public static List<Rental> getListRentalByIdListInventory(List<Inventory> listInventory, Session session) {
        try {
            Query<Rental> queryRental = session.createQuery("from Rental where inventory in (:list)", Rental.class);
            queryRental.setParameter("list", listInventory);
            List<Rental> listR = queryRental.list();

            return listR;
        } catch (Throwable err) {
            session.close();
            throw err;
        }
    }

    public static void createRentalWithInventoryExist(Customer customer, Inventory inventory,  Session session) {
        try {
            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setStaff(customer.getStore().getStaff());

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(customer.getStore().getStaff());
            payment.setRental(rental);
            payment.setAmount(new BigDecimal("3.55"));
            payment.setPaymentDate(LocalDateTime.now());

            session.beginTransaction();
            session.persist(rental);
            session.persist(payment);
            session.getTransaction().commit();
        } catch (Throwable err) {
            session.getTransaction().rollback();
            session.close();
            throw err;
        }
    }

    public static void createRentalWithInventoryNotExist(Customer customer, Film film, Session session) {
        try {
            Inventory inventory = new Inventory();
            inventory.setFilm(film);
            inventory.setStore(customer.getStore());

            Rental rental = new Rental();
            rental.setRentalDate(LocalDateTime.now());
            rental.setInventory(inventory);
            rental.setCustomer(customer);
            rental.setStaff(customer.getStore().getStaff());

            Payment payment = new Payment();
            payment.setCustomer(customer);
            payment.setStaff(customer.getStore().getStaff());
            payment.setRental(rental);
            payment.setAmount(new BigDecimal("3.55"));
            payment.setPaymentDate(LocalDateTime.now());

            session.beginTransaction();
            session.persist(inventory);
            session.persist(rental);
            session.persist(payment);
            session.getTransaction().commit();
        } catch (Throwable err) {
            session.getTransaction().rollback();
            session.close();
            throw err;
        }
    }
}
