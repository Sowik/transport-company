package entity;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "vehicle")
public class Vehicle {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name="vehicle_type", nullable = false)
    private VehicleType type;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "total_trips")
    private int totalTrips;

    @Column(name = "is_available")
    private boolean is_available = true;

    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Trip> allTrips = new HashSet<>();


    public Vehicle() {
        this.brand = "";
        this.type = VehicleType.CargoTruck;
    }

    public Vehicle(String brand, VehicleType type, Company company) {
        this.brand = brand;
        this.type = type;
        this.company = company;
    }

    public Company getCompany() {
        return company;
    }

    public String getBrand() {
        return brand;
    }

    public VehicleType getType() {
        return type;
    }

    public boolean is_available() {
        return is_available;
    }

    public void set_is_available(){
        is_available = true;
        plusTotalTrips();
    }


    public void set_is_not_available(){
        is_available = false;
    }

    public void plusTotalTrips(){
        totalTrips+=1;
    }

    public void addTrip(Trip trip){
        allTrips.add(trip);
    }

    public Set<Trip> getAllTrips() {
        return allTrips;
    }

    @Override
    public String toString() {
        return "entity.Vehicle{" +
                "brand='" + brand + '\'' +
                ", type=" + type +
                '}';
    }
}
