package entity;

import dao.DriverDAO;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="revenue", nullable = false)
    private BigDecimal totalRevenue;

    @OneToMany(mappedBy = "company")
    private Set<Driver> driversList = new HashSet<>();

    @OneToMany(mappedBy = "company")
    private Set<Vehicle> vehicleList = new HashSet<>();

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Trip> tripsList = new HashSet<>();


    public Company(){

    }

    public Company(String name){
        this.name = name;
        totalRevenue = BigDecimal.valueOf(0);
    }


    public void addEmployee(Driver employee) {
        this.driversList.add(employee);
    }

    public void setSalary(Driver driver){
        Set<DriverCategory> categories = driver.getCategories();

        for(DriverCategory dc: categories){
            System.out.println("----------------------Incrementing");
            driver.incrementSalary(BigDecimal.valueOf(200));
            if(dc == DriverCategory.ADR){
                driver.incrementSalary(BigDecimal.valueOf(300));
            }
        }
    }

    public void removeEmployee(Driver employee) {
        this.driversList.remove(employee);
    }

    public void addVehicle(Vehicle vehicle){
        this.vehicleList.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle) {this.vehicleList.remove(vehicle);}

    public String getName() {
        return name;
    }

    public void addRevenue(BigDecimal money){
        totalRevenue = totalRevenue.add(money);
    }

    public long getId() {
        return id;
    }

    public void addTrip(Trip trip){
        tripsList.add(trip);
    }

    public long countTrips(){

        Set<Trip> tripsCount = getTripsList();
        long total = tripsCount.stream()
                .filter(trip -> trip.isIs_done())
                .count();

        return total;
    }

    public void revenueFromEachTrip(){
        tripsList.stream()
                .filter(trip -> trip.isIs_done())
                .forEach(trip -> System.out.println("Trip ID: " + trip.getId() + " Revenue from trip: " + trip.getPrice()));

    }

    public void revenueFromEachDriver(){
        List<BigDecimal> revenues = new ArrayList<>();
        driversList.forEach(driver -> driver.getAllTrips().stream().filter(trip -> trip.isIs_done()).forEach(trip -> revenues.add(trip.getPrice())));

        BigDecimal result = revenues.stream()
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        System.out.println(result);
    }

    public Set<Driver> getDriversList() {
        return driversList;
    }

    public Set<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public BigDecimal getTotalRevenue() {
        return totalRevenue;
    }

    public Set<Trip> getTripsList() {
        return tripsList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", totalRevenue=" + totalRevenue +
                '}';
    }

}
