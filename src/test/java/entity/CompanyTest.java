package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {

    private Company company;

    @BeforeEach
    void init(){
        company = new Company("Biomet");
    }

    @Test
    void addEmployee() {
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company);
        Set<Driver> expected = new HashSet<>();
        expected.add(driver1);
        company.addEmployee(driver1);

        assertEquals(expected,company.getDriversList());
    }

    @Test
    void setSalary() {
        BigDecimal expected = BigDecimal.valueOf(1200);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company);

        company.setSalary(driver1);

        assertEquals(expected,driver1.getSalary());
    }

    @Test
    void removeEmployee() {
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company);
        Set<Driver> expected = new HashSet<>();

        company.addEmployee(driver1);
        company.removeEmployee(driver1);

        assertEquals(expected,company.getDriversList());
    }

    @Test
    void addVehicle() {
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company);
        Set<Vehicle> expected = new HashSet<>();
        expected.add(truck1);

        company.addVehicle(truck1);
        assertEquals(expected,company.getVehicleList());
    }

    @Test
    void removeVehicle() {
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company);
        Set<Vehicle> expected = new HashSet<>();

        company.addVehicle(truck1);
        company.removeVehicle(truck1);
        assertEquals(expected,company.getVehicleList());
    }

    @Test
    void getName() {
        String expected = "Biomet";
        assertEquals(expected,company.getName());
    }

    @Test
    void addRevenue() {
        BigDecimal expected = BigDecimal.valueOf(500);
        company.addRevenue(BigDecimal.valueOf(500));

        assertEquals(expected,company.getTotalRevenue());
    }

    @Test
    void getId() {
        long expected = 0;
        long actual = company.getId();
        assertEquals(expected,actual);
    }

    @Test
    void addTrip() throws ParseException, NotAvailableException, WrongVehicleException {
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company);
        Customer customer1 = new Customer("Firma1");
        Trip trip1 = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck,BigDecimal.valueOf(10), BigDecimal.valueOf(500), company,customer1,driver1,truck1);

        Set<Trip> expected = new HashSet<>();
        expected.add(trip1);

        company.addTrip(trip1);

        assertEquals(expected,company.getTripsList());
    }

    @Test
    void countTrips() throws ParseException, NotAvailableException, WrongVehicleException {
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company);
        Customer customer1 = new Customer("Firma1");
        Trip trip1 = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck,BigDecimal.valueOf(10), BigDecimal.valueOf(500), company,customer1,driver1,truck1);

        long expected = 1;
        company.addTrip(trip1);
        trip1.setDone();
        assertEquals(expected,company.countTrips());
    }

}