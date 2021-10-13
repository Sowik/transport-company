package entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "driver")
public class Driver {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "total_trips")
    private int totalTrips;

    @Column(name = "salary")
    private BigDecimal salary = BigDecimal.valueOf(1000);

    @Column(name = "is_available")
    private boolean is_available = true;

    @Enumerated(EnumType.STRING)
    @Column(name = "category1", nullable = false)
    private DriverCategory category1;


    @OneToMany(mappedBy = "vehicle", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private Set<Trip> allTrips = new HashSet<>();

    public Driver(){
        this.name = "John Doe";

    }

    public Driver(String name, DriverCategory category, Company company){

        this.name = name;
        this.company = company;
        this.category1 = category;

    }

    public Company getCompany() {
        return company;
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
        totalTrips+= 1;
    }


    public String getName() {
        return name;
    }
    
    public Set<DriverCategory> getCategories(){
        Set<DriverCategory> categories = new HashSet<>();
        categories.add(category1);

//        if(category3 != null){
//            categories.add(category3);
//        }
//        else if(category2 != null) {
//            categories.add(category2);
//        }

        return categories;
    }

    public void incrementSalary(BigDecimal money){
        salary = salary.add(money);
    }

    public void addTrip(Trip trip){
        allTrips.add(trip);
    }

    public Set<Trip> getAllTrips() {
        return allTrips;
    }

    public long getId() {
        return id;
    }

    public BigDecimal getRevenueFromTrips() {
        List<BigDecimal> revenues = new ArrayList<>();

        allTrips.stream()
                .filter(trip -> trip.isIs_done())
                .forEach(trip -> revenues.add(trip.getPrice()));

        BigDecimal results = revenues.stream()
                .reduce(BigDecimal.ZERO,BigDecimal::add);

        return results;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", company=" + company +
                ", totalTrips=" + totalTrips +
                ", salary=" + salary +
                ", is_available=" + is_available +
                ", category1=" + category1 +
                '}';
    }
}
