package dao;

import configuration.SessionFactoryUtil;
import entity.Company;
import entity.Driver;
import entity.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.List;

public class TripDAO {

    public static void saveTrip(Trip trip) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            trip.getDriver().addTrip(trip);
            trip.getVehicle().addTrip(trip);
            trip.getCompany().addTrip(trip);
            session.save(trip);
            transaction.commit();
        }
    }

    public static void saveOrUpdateTrip(Trip trip) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            trip.getDriver().addTrip(trip);
            trip.getVehicle().addTrip(trip);
            session.saveOrUpdate(trip);
            transaction.commit();
        }
    }


    public static List<Trip> getTrips() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Trip", Trip.class).list();
        }
    }

    public static List<Trip> getTripsByDestination() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Trip order BY finish", Trip.class).list();
        }
    }

    public static List<Trip> getTripsByCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Trip WHERE company_id = " + company.getId(), Trip.class).list();
        }
    }

    public static List<Trip> getTripsByCompany(long id) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Trip WHERE company_id = " + id, Trip.class).list();
        }
    }

    public static List<Trip> getTripsByDriver(long driverId) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Trip> cr = cb.createQuery(Trip.class);
            Root<Trip> root = cr.from(Trip.class);
            cr.select(root).where(cb.equal(root.get("driver_id"), driverId));

            Query<Trip> query = session.createQuery(cr);
            List<Trip> tripsByDrivers = query.getResultList();
            return tripsByDrivers;
        }
    }

    public static Trip getTrip(long id) {
        Transaction transaction = null;
        Trip trip;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get CompanyEvent entity using get() method
            trip = session.get(Trip.class, id);
            transaction.commit();
        }
        return trip;
    }

    public static Trip loadTrip(long id) {
        Transaction transaction = null;
        Trip trip;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get CompanyEvent entity using load() method
            trip = session.load(Trip.class, id);
            transaction.commit();
        }
        return trip;
    }

    public static Trip getTripById(long id) {
        Transaction transaction = null;
        Trip trip;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get CompanyEvent entity using byId() method
            trip = session.byId(Trip.class).getReference(id);
            transaction.commit();
        }
        return trip;
    }

    public static void deleteTrip(Trip trip) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(trip);
            transaction.commit();
        }
    }
}