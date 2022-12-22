package pl.edu.agh.debtexecutor.groups;

import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.expenses.Expense;
import pl.edu.agh.debtexecutor.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;
import java.util.UUID;

@Entity
@Table(name = "groups")
public class Group {
    private static final int MAX_PLACEHOLDER_LENGTH = 64;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "group_name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Group_User",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
    private List<User> members = new ArrayList<>();

    @OneToMany(mappedBy = "group")
    private final List<Expense> expenses = new ArrayList<>();

    public Group() {}

    public Group(List<User> members) {
        this.members = members;
    }

    public Group(String name, List<User> members) {
        this.name = name;
        this.members = members;
    }

    public UUID getId() {
        return id;
    }

    public List<User> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public String getPlaceholderName() {
        StringJoiner joiner = new StringJoiner(", ");

        for (User member: members) {
            String name = member.getFirstName().charAt(0) + ". " + member.getLastName();
            if (joiner.length() + name.length() > MAX_PLACEHOLDER_LENGTH) {
                joiner.add(name.substring(0, MAX_PLACEHOLDER_LENGTH - joiner.length()) + "...");
                break;
            }
            joiner.add(name);
        }

        return joiner.toString();
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
