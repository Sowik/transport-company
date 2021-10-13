package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    private Vehicle truck;

    @BeforeEach
    void setUp() {
        Company company1 = new Company("Biomet");
        truck = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
    }

    @Test
    void getCompany() {
        Company company1 = new Company("Biomet");
        Vehicle truck = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);

        Company expected = company1;
        assertEquals(expected,truck.getCompany());
    }

    @Test
    void getBrand() {
        String expected = "Volvo FM 1";

        assertEquals(expected,truck.getBrand());
    }

    @Test
    void is_available() {
        assertTrue(truck.is_available());
    }

    @Test
    void set_is_available() {
        truck.set_is_available();
        assertTrue(truck.is_available());
    }

    @Test
    void set_is_not_available() {
        truck.set_is_not_available();
        assertFalse(truck.is_available());
    }

    @Test
    void addTrip() throws ParseException, NotAvailableException, WrongVehicleException {

        Company company1 = new Company("Biomet");
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Customer customer1 = new Customer("Firma1");

        Trip trip = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer1,driver1,truck);

        truck.addTrip(trip);

        Set<Trip> expected = new HashSet<>();
        expected.add(trip);

        assertEquals(expected,truck.getAllTrips());
    }
}