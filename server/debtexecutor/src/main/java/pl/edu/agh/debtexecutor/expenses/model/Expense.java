package pl.edu.agh.debtexecutor.expenses.model;

import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.categories.model.Category;
import pl.edu.agh.debtexecutor.groups.model.Group;
import pl.edu.agh.debtexecutor.users.model.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public Expense() {}

    public Expense(String title,
                   User payer,
                   User payee,
                   BigDecimal amount,
                   Group group,
                   Category category) {
        this.title = title;
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
        this.group = group;
        this.category = category;
        this.date = LocalDateTime.now();
    }

    public Expense(String title,
                   User payer,
                   User payee,
                   BigDecimal amount,
                   Category category) {
        this(title, payer, payee, amount, null, category);
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

    public Optional<Category> getCategory() {
        return Optional.ofNullable(category);
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
