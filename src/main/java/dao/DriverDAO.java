package dao;

import configuration.SessionFactoryUtil;
import entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class DriverDAO {

    public static void saveDriver(Driver employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee.getCompany().addEmployee(employee);
            employee.getCompany().setSalary(employee);
            session.save(employee);
            transaction.commit();
        }
    }

    public static void saveOrUpdateDriver(Driver employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee.getCompany().addEmployee(employee);
            session.saveOrUpdate(employee);
            transaction.commit();
        }
    }

    public static void saveDrivers(List<Driver> employeeList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employeeList.stream().forEach(employee -> employee.getCompany().addEmployee(employee));
            employeeList.stream().forEach(employee -> session.save(employee));
            transaction.commit();
        }
    }

    public static List<Driver> getDrivers() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Driver", Driver.class).list();
        }
    }

    public static List<Driver> getDriversByCategoryThenSalary() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Driver order BY category1, salary desc", Driver.class).list();
        }
    }

    public static Driver getDriver(long id) {
        Transaction transaction = null;
        Driver employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Employee entity using get() method
            employee = session.get(Driver.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static Driver loadDriver(long id) {
        Transaction transaction = null;
        Driver employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Employee entity using load() method
            employee = session.load(Driver.class, id);
            transaction.commit();
        }
        return employee;
    }

    public static Driver getDriverById(long id) {
        Transaction transaction = null;
        Driver employee;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Employee entity using byId() method
            employee = session.byId(Driver.class).getReference(id);
            transaction.commit();
        }
        return employee;
    }

    public static void deleteDriver(Driver employee) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            employee.getCompany().removeEmployee(employee);
            session.delete(employee);
            transaction.commit();
        }
    }



}