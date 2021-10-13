package entity;

import dao.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "start", nullable = false)
    private String startingPoint;

    @Column(name = "finish", nullable = false)
    private String endPoint;

    @Column(name = "date_dispatched", nullable = false)
    private Date dispatched;

    @Column(name = "date_delivery", nullable = false)
    private Date delivery;

    @Column(name = "cargo_type", nullable = false)
    private VehicleType cargoType;

    @Column(name = "cargo_size", nullable = false)
    private BigDecimal cargoSize;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "is_done")
    private boolean is_done = false;

    public Trip(){

    }

    public Trip(String startingPoint,String endPoint, String dispatch, String delivery,
                VehicleType cargo, BigDecimal cargoSize, BigDecimal price, Company company,
                Customer customer, Driver driver, Vehicle vehicle) throws ParseException, WrongVehicleException, NotAvailableException {
        this.startingPoint = startingPoint;
        this.endPoint = endPoint;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.dispatched = sdf.parse(dispatch);
        this.delivery = sdf.parse(delivery);
        this.cargoType = cargo;
        this.company = company;
        this.customer = customer;


        if(!driver.is_available()){
            throw new NotAvailableException("Driver is not available!");
        }
        else if(!vehicle.is_available()){
            throw new NotAvailableException("Vehicle is not available!");
        }
        else {
            this.driver = driver;
            driver.set_is_not_available();

            this.vehicle = vehicle;
            vehicle.set_is_not_available();
        }

        if(this.cargoType == VehicleType.Bus) {
            throw new WrongVehicleException("Not a valid vehicle type for this kind of cargo!");
        }

        this.cargoSize = cargoSize;
        this.price = price;

        customer.setMoneyDue(price);
    }


    public Customer getCustomer() {
        return customer;
    }

    public Company getCompany() {
        return company;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setDone(){
        driver.set_is_available();
        vehicle.set_is_available();

        this.is_done = true;

        customer.payCompany(company,price);

        CustomerDAO.saveOrUpdateCustomer(customer);
        CompanyDAO.saveOrUpdateCompany(company);
        DriverDAO.saveOrUpdateDriver(driver);
        VehicleDAO.saveOrUpdateEmployee(vehicle);
        TripDAO.saveOrUpdateTrip(this);
    }

    public Driver getDriver() {
        return driver;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public boolean isIs_done(){
        return is_done;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", startingPoint='" + startingPoint + '\'' +
                ", endPoint='" + endPoint + '\'' +
                ", dispatched=" + dispatched +
                ", delivery=" + delivery +
                ", cargoType=" + cargoType +
                ", cargoSize=" + cargoSize +
                ", price=" + price +
                ", customer=" + customer +
                ", company=" + company +
                '}';
    }
}
