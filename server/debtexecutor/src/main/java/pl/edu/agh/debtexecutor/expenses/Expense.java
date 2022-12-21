package pl.edu.agh.debtexecutor.expenses;

import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.groups.Group;
import pl.edu.agh.debtexecutor.users.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "expenses")
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
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

    public Expense() {}

    public Expense(String title,
                   User payer,
                   User payee,
                   Group group,
                   BigDecimal amount) {
        this.title = title;
        this.payer = payer;
        this.payee = payee;
        this.group = group;
        this.amount = amount;
        this.date = LocalDateTime.now();
    }

    public Expense(String title,
                   User payer,
                   User payee,
                   BigDecimal amount) {
        this(title, payer, payee, null, amount);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }
}
