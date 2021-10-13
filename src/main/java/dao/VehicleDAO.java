package dao;

import configuration.SessionFactoryUtil;
import entity.Vehicle;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.List;

public class VehicleDAO {

        public static void saveEmployee(Vehicle vehicle) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            vehicle.getCompany().addVehicle(vehicle);
            session.save(vehicle);
            transaction.commit();
        }
    }

        public static void saveOrUpdateEmployee(Vehicle employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee.getCompany().addVehicle(employee);
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

        public static void saveEmployees(List<Vehicle> employeeList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeList.stream().forEach(employee -> employee.getCompany().addVehicle(employee));
            employeeList.stream().forEach(employee -> session.save(employee));
            transaction.commit();
        }
    }

        public static List<Vehicle> getEmployees() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Employee", Vehicle.class).list();
        }
    }

        public static Vehicle getEmployee(long id) {
        Transaction transaction = null;
        Vehicle employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Employee entity using get() method
            employee = session.get(Vehicle.class, id);
            transaction.commit();
        }
        return employee;
    }

        public static Vehicle loadEmployee(long id) {
        Transaction transaction = null;
        Vehicle employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Employee entity using load() method
            employee = session.load(Vehicle.class, id);
            transaction.commit();
        }
        return employee;
    }

        public static Vehicle getEmployeeById(long id) {
        Transaction transaction = null;
        Vehicle employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Employee entity using byId() method
            employee = session.byId(Vehicle.class).getReference(id);
            transaction.commit();
        }
        return employee;
    }

        public static void deleteEmployee(Vehicle employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee.getCompany().removeVehicle(employee);
            session.delete(employee);
            transaction.commit();
        }
    }


}
