package pl.edu.agh.debtexecutor.categories;

import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.expenses.Expense;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(
            name = "name",
            nullable = false,
            unique = true
    )
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private final List<Expense> expenses = new ArrayList<>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addExpense(Expense expense) {
        expenses.add(expense);
    }

    public List<Expense> getExpenses() {
        return expenses;
    }
}
