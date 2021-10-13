package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class TripTest {

    private Trip trip;

    @BeforeEach
    void setUp() throws ParseException, NotAvailableException, WrongVehicleException {
        Company company1 = new Company("Biomet");
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Customer customer1 = new Customer("Firma1");

        trip = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver1,truck1);
    }

    @Test
    void getCustomer() throws ParseException, NotAvailableException, WrongVehicleException {
        Company company1 = new Company("Biomet");
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Customer customer1 = new Customer("Firma1");

        Customer expected = customer1;

        trip = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver1,truck1);

        assertEquals(expected,trip.getCustomer());
    }

    @Test
    void getCompany() throws ParseException, NotAvailableException, WrongVehicleException {

        Company company1 = new Company("Biomet");
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Customer customer1 = new Customer("Firma1");

        Company expected = company1;

        trip = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver1,truck1);

        assertEquals(expected,trip.getCompany());
    }

    @Test
    void getPrice() {
        BigDecimal expected = BigDecimal.valueOf(500);

        assertEquals(expected,trip.getPrice());
    }

    @Test
    void setDone() {
        trip.setDone();

        assertEquals(true,trip.isIs_done());
    }

    @Test
    void getDriver() throws ParseException, NotAvailableException, WrongVehicleException {
        Company company1 = new Company("Biomet");
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Customer customer1 = new Customer("Firma1");

        Driver expected = driver1;

        trip = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver1,truck1);

        assertEquals(expected,trip.getDriver());
    }

    @Test
    void getVehicle() throws ParseException, NotAvailableException, WrongVehicleException {

        Company company1 = new Company("Biomet");
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Customer customer1 = new Customer("Firma1");

        Vehicle expected = truck1;

        trip = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver1,truck1);

        assertEquals(expected,trip.getVehicle());
    }


    @Test
    void getId() {
        long expected = 0;
        assertEquals(expected,trip.getId());
    }
}