package dao;

import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import configuration.SessionFactoryUtil;
import entity.Company;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CompanyDAO {

    public static void saveCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(company);
            transaction.commit();
        }
    }

    public static void saveOrUpdateCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(company);
            transaction.commit();
        }
    }

    public static void saveCompanies(List<Company> companyList) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            companyList.stream().forEach((com) -> session.save(com));
            transaction.commit();
        }
    }

    public static List<Company> getCompanies() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Company", Company.class).list();
        }
    }

    public static List<Company> getCompaniesSortByNameThenRevenue() {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Company order BY name, revenue desc", Company.class).list();
        }
    }

    public static void getCompaniesToTxt(String outputFilename) throws IOException {
        List<Company> companies;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
             companies =  session.createQuery("FROM Company order BY name, revenue desc", Company.class).list();
        }

        try{
            FileWriter writer = new FileWriter(outputFilename);
            for(Company str: companies) {
                writer.write(str + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        readFromFile(outputFilename);
    }

    public static void readFromFile(String outputFilename) throws IOException {
        List<String> companies = new ArrayList<>();
        try {
            File myObj = new File(outputFilename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                companies.add(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        companies.forEach(System.out::println);
    }

    public static Company getCompany(long id) {
        Transaction transaction = null;
        Company company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Company entity using get() method
            company = session.get(Company.class, id);
            transaction.commit();
        }
        return company;
    }

    public static Company loadCompany(long id) {
        Transaction transaction = null;
        Company company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Company entity using load() method
            company = session.load(Company.class, id);
            transaction.commit();
        }
        return company;
    }

    public static Company getCompanyById(long id) {
        Transaction transaction = null;
        Company company;
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            // get Company entity using byId() method
            company = session.byId(Company.class).getReference(id);
            transaction.commit();
        }
        return company;
    }

    public static void deleteCompany(Company company) {
        try (Session session = SessionFactoryUtil.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.delete(company);
            transaction.commit();
        }
    }



}