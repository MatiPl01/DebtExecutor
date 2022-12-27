package pl.edu.agh.debtexecutor.categories.model;

import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.users.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private final List<Expense> expenses = new ArrayList<>();

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!getClass().isInstance(obj)) return false;
        return id.equals(((Category) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
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
