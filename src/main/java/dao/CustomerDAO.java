package dao;

import configuration.SessionFactoryUtil;
import entity.Customer;
import entity.Driver;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CustomerDAO {

    public static void saveCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(customer);
            transaction.commit();
        }
    }

    public static void saveOrUpdateCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(customer);
            transaction.commit();
        }
    }

    public static void deleteCustomer(Customer customer) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(customer);
            transaction.commit();
        }
    }

}
