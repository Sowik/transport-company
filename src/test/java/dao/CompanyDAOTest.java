package dao;

import entity.Company;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyDAOTest {

    private Company company;

    @BeforeEach
    void init(){
        company = new Company("Biomet");
    }

    @Test
    void saveCompany() {

    }

    @Test
    void saveOrUpdateCompany() {
    }

    @Test
    void saveCompanies() {
    }

    @Test
    void getCompanies() {
    }

    @Test
    void getCompaniesSortByNameThenRevenue() {
    }

    @Test
    void getCompaniesToTxt() {
    }

    @Test
    void readFromFile() {
    }

    @Test
    void getCompany() {
    }

    @Test
    void loadCompany() {
    }

    @Test
    void getCompanyById() {
    }

    @Test
    void deleteCompany() {
    }
}