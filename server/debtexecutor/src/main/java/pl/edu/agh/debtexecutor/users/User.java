package pl.edu.agh.debtexecutor.users;

import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.groups.Group;

import java.math.BigDecimal;
import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(
            name = "login",
            nullable = false,
            unique = true
    )
    private String login;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "total_balance",
            nullable = false
    )
    private BigDecimal totalBalance = BigDecimal.ZERO;
    @ElementCollection
    @CollectionTable(
            name = "user_balance",
            joinColumns = { @JoinColumn(
                    name = "user_id",
                    referencedColumnName = "id"
            ) }
    )
    @MapKeyColumn(name = "user_id")
    @Column(name = "balance")
    private final Map<User, BigDecimal> balance = new HashMap<>();

    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY)
    List<Group> groups = new ArrayList<>();

    public User() {}

    public User(String login, String firstName, String lastName) {
        this.login = login;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().isInstance(obj)) return false;
        return id.equals(((User) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    public UUID getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void addToGroup(Group group) {
        groups.add(group);
    }

    public Map<User, BigDecimal> getBalance() {
        return balance;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void changeBalance(User user, BigDecimal amount) {
        BigDecimal currBalance = amount
            .add(Optional.ofNullable(balance.get(user))
            .orElse(BigDecimal.ZERO));

        totalBalance = totalBalance.add(amount);

        if (currBalance.equals(BigDecimal.ZERO)) {
            balance.remove(user);
        } else {
            balance.put(user, currBalance);
        }
    }
}
