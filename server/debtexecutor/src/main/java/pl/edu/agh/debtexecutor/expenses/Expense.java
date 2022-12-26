package pl.edu.agh.debtexecutor.expenses;

import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.categories.Category;
import pl.edu.agh.debtexecutor.groups.Group;
import pl.edu.agh.debtexecutor.users.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "payer_id")
    private User payer;

    @ManyToOne
    @JoinColumn(name = "payee_id")
    private User payee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Expense_Category",
            joinColumns = { @JoinColumn(name = "category_id") },
            inverseJoinColumns = { @JoinColumn(name = "expense_id") }
    )
    private List<Category> categories;

    public Expense() {}

    public Expense(String title,
                   User payer,
                   User payee,
                   Group group,
                   List<Category> categories,
                   BigDecimal amount) {
        this.title = title;
        this.payer = payer;
        this.payee = payee;
        this.group = group;
        this.amount = amount;
        this.categories = categories;
        this.date = LocalDateTime.now();
    }

    public Expense(String title,
                   User payer,
                   User payee,
                   Group group,
                   BigDecimal amount) {
        this(title, payer, payee, group, List.of(), amount);
    }

    public Expense(String title,
                   User payer,
                   User payee,
                   List<Category> categories,
                   BigDecimal amount) {
        this(title, payer, payee, null, categories, amount);
    }

    public Expense(String title,
                   User payer,
                   User payee,
                   BigDecimal amount) {
        this(title, payer, payee, null, List.of(), amount);
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public User getPayer() {
        return payer;
    }

    public User getPayee() {
        return payee;
    }

    public Optional<Group> getGroup() {
        return Optional.ofNullable(group);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
