import dao.*;
import entity.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Main {

    public static void main(String[] args) throws WrongVehicleException, ParseException, NotAvailableException, IOException {

//        Company company1 = new Company("Biomet");
//        Company company2 = new Company("Abc");
//        Company company3 = new Company("World Tranport");
//        Company company4 = new Company("Union");
//
//
//        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
//        Vehicle truck2 = new Vehicle("Volvo FM 2", VehicleType.Tanker, company1);
//        Vehicle truck3 = new Vehicle("Mercedes GM", VehicleType.CargoTruck, company2);
//        Vehicle truck4 = new Vehicle("Mercedes PP", VehicleType.Tanker, company2);
//
//
//        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
//        Driver driver2 = new Driver("Kalin Kalinov", DriverCategory.D, company1);
//        Driver driver3 = new Driver("Pop Smoke", DriverCategory.ADR, company1);
//        Driver driver4 = new Driver("Juliqn Jekov", DriverCategory.C, company1);
//        Driver driver5 = new Driver("Todor Rodor", DriverCategory.C, company1);
//        Driver driver6 = new Driver("Au Bau", DriverCategory.ADR, company1);
//
//        Customer customer1 = new Customer("Firma1");
//        Customer customer2 = new Customer("Benedetta");
//
//
//        Trip trip1 = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
//                VehicleType.CargoTruck,BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver1,truck1);
//        Trip trip2 = new Trip("Varna", "Budapest","15/12/2020", "16/12/2020",VehicleType.Tanker,
//                BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver2,truck2);
//
//        Trip trip3 = new Trip("Sofia", "Orlando","12/12/2020", "21/12/2020",
//                VehicleType.CargoTruck,BigDecimal.valueOf(10), BigDecimal.valueOf(1420), company1,customer1,driver3,truck3);
//
//        Trip trip4 = new Trip("Varna", "Gorno Orqhovo","14/12/2020", "18/12/2020",VehicleType.Tanker,
//                BigDecimal.valueOf(10), BigDecimal.valueOf(600), company1,customer1,driver4,truck4);
//
//        CompanyDAO.saveCompany(company1);
//        CompanyDAO.saveCompany(company2);
//        CompanyDAO.saveCompany(company3);
//        CompanyDAO.saveCompany(company4);
//
//        CustomerDAO.saveCustomer(customer1);
//
//        VehicleDAO.saveEmployee(truck1);
//        VehicleDAO.saveEmployee(truck2);
//        VehicleDAO.saveEmployee(truck3);
//        VehicleDAO.saveEmployee(truck4);
//
//        DriverDAO.saveDriver(driver1);
//        DriverDAO.saveDriver(driver2);
//        DriverDAO.saveDriver(driver3);
//        DriverDAO.saveDriver(driver4);
//        DriverDAO.saveDriver(driver5);
//        DriverDAO.saveDriver(driver6);
//
//        TripDAO.saveTrip(trip1);
//        TripDAO.saveTrip(trip2);
//        TripDAO.saveTrip(trip3);
//        TripDAO.saveTrip(trip4);
//
//        trip1.setDone();
//        trip3.setDone();


        //Output companies sorted BY name then revenue
        List<Company> companies = CompanyDAO.getCompaniesSortByNameThenRevenue();
        companies.forEach(System.out::println);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Output companies sorted BY name then revenue^^^^^^^^^^^^^^^^^^^^^^^^^^");


        //Output drivers sorted by category then by salary
        List<Driver> drivers = DriverDAO.getDriversByCategoryThenSalary();
        drivers.forEach(System.out::println);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Output drivers sorted by category then by salary^^^^^^^^^^^^^^^^^^^^^^^^^^");


        //Get trips by destination
        List<Trip> trips = TripDAO.getTripsByDestination();
        trips.forEach(System.out::println);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Get trips by destination^^^^^^^^^^^^^^^^^^^^^^^^^^");



        CompanyDAO.getCompaniesToTxt("company1.txt");
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Companies to txt, read file^^^^^^^^^^^^^^^^^^^^^^^^^^");

        //Get all trips for a given company
        List<Trip> tripsByCompany = TripDAO.getTripsByCompany(CompanyDAO.getCompany(1));
        System.out.println(tripsByCompany);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Get all trips for a given company^^^^^^^^^^^^^^^^^^^^^^^^^^");

        //Number of trips a given company has done
        Company companyX = CompanyDAO.getCompany(1);
        System.out.println(companyX.countTrips());
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Number of trips a given company has done^^^^^^^^^^^^^^^^^^^^^^^^^^");

        //Get revenue from each trip done by a given company
        Company companyY = CompanyDAO.getCompany(1);
        companyY.revenueFromEachTrip();
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Get revenue from each trip done by a given company^^^^^^^^^^^^^^^^^^^^^^^^^^");

        List<Driver> driversRevenue = DriverDAO.getDrivers();
        driversRevenue.forEach(driver -> System.out.println(driver.getName() + " has made "
                + driver.getRevenueFromTrips() + " for company " + driver.getCompany() + "!"));
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^Revenue from each driver^^^^^^^^^^^^^^^^^^^^^^^^^^");
    }
}
