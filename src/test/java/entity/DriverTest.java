package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DriverTest {

    private Driver driver;

    @BeforeEach
    void setUp() {
        Company company = new Company("Biomet");
        driver = new Driver("Ivan Ivanov", DriverCategory.C, company);
    }

    @Test
    void getCompany() {
        Company company = new Company("Biomet");
        Driver driver1 = new Driver("Ivan Ivanov", DriverCategory.C, company);
        Company expected = company;

        assertEquals(expected,driver1.getCompany());
    }

    @Test
    void is_available() {
        assertTrue(driver.is_available());
    }


    @Test
    void set_is_not_available() {
        driver.set_is_not_available();
        assertFalse(driver.is_available());
    }

    @Test
    void getName() {
        String expected = "Ivan Ivanov";
        assertEquals(expected,driver.getName());
    }

    @Test
    void getCategories() {
        Set<DriverCategory> expected = new HashSet<>();
        expected.add(DriverCategory.C);
        assertEquals(expected,driver.getCategories());
    }


    @Test
    void getAllTrips() {
        Set<Trip> expected = new HashSet<>();
        assertEquals(expected,driver.getAllTrips());
    }

    @Test
    void getId() {
        long expected = 0;
        assertEquals(expected,driver.getId());
    }

    @Test
    void getSalary() {
        BigDecimal expected = BigDecimal.valueOf(1000);

        assertEquals(expected,driver.getSalary());
    }
}