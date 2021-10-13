package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CustomerTest {

    private Customer customer;

    @BeforeEach
    void init(){
        customer = new Customer("Klienta1");
    }

    @Test
    public void addTrip() throws ParseException, NotAvailableException, WrongVehicleException {
        Company company1 = new Company("Biomet");
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Trip trip1 = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer,driver1,truck1);

        Set<Trip> expected = new HashSet<>();
        expected.add(trip1);

        customer.addTrip(trip1);

        assertEquals(expected,customer.getTripsSet());
    }

    @Test
    public void removeTrip() throws ParseException, NotAvailableException, WrongVehicleException {
        Company company1 = new Company("Biomet");
        Vehicle truck1 = new Vehicle("Volvo FM 1", VehicleType.CargoTruck, company1);
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company1);
        Trip trip1 = new Trip("Varna", "Athens","12/12/2020", "13/12/2020",
                VehicleType.CargoTruck, BigDecimal.valueOf(10), BigDecimal.valueOf(500), company1,customer,driver1,truck1);

        Set<Trip> expected = new HashSet<>();

        customer.addTrip(trip1);
        customer.removeTrip(trip1);

        assertEquals(expected,customer.getTripsSet());
    }

    @Test
    void setMoneyDue() {
        BigDecimal expected = BigDecimal.valueOf(500);
        customer.setMoneyDue(BigDecimal.valueOf(500));

        assertEquals(expected,customer.getMoneyDue());
    }

    @Test
    void removeMoneyDue() {
        BigDecimal expected = BigDecimal.valueOf(0);
        customer.setMoneyDue(BigDecimal.valueOf(500));
        customer.removeMoneyDue(BigDecimal.valueOf(500));

        assertEquals(expected,customer.getMoneyDue());
    }

    @Test
    void getMoneyDue() {
        BigDecimal expected = BigDecimal.valueOf(0);
        customer.setMoneyDue(BigDecimal.valueOf(0));

        assertEquals(expected,customer.getMoneyDue());
    }
}