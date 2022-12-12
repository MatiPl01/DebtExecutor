package pl.edu.agh.debtexecutor.group;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.expense.Expense;
import pl.edu.agh.debtexecutor.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "group_name")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Group_User",
            joinColumns = { @JoinColumn(name = "id") },
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

    public List<User> getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    public List<Expense> getExpenses() {
        return expenses;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }
}
