package entity;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "money_due")
    private BigDecimal moneyDue = BigDecimal.valueOf(0);

    @OneToMany(mappedBy = "customer")
    private Set<Trip> tripsSet = new HashSet<>();

    public Customer(){

    }

    public Customer(String name){
        this.name = name;
    }

    public void addTrip(Trip trip) {
        this.tripsSet.add(trip);
    }
    public void removeTrip(Trip trip) {
        this.tripsSet.remove(trip);
    }

    public void setMoneyDue(BigDecimal money){
        moneyDue = moneyDue.add(money);
    }

    public void removeMoneyDue(BigDecimal money) {
        moneyDue = moneyDue.subtract(money);
        System.out.println(moneyDue);
    }

    public void payCompany(Company company,BigDecimal money){
        removeMoneyDue(money);
        company.addRevenue(money);
    }

    public BigDecimal getMoneyDue() {
        return moneyDue;
    }

    public Set<Trip> getTripsSet() {
        return tripsSet;
    }
}
