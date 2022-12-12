package pl.edu.agh.debtexecutor.expense;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import pl.edu.agh.debtexecutor.group.Group;
import pl.edu.agh.debtexecutor.user.User;

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
                   BigDecimal amount,
                   LocalDateTime date) {
        this.title = title;
        this.payer = payer;
        this.payee = payee;
        this.amount = amount;
        this.date = date;
    }

    public Expense(String title,
                   User payer,
                   User payee,
                   Group group,
                   BigDecimal amount,
                   LocalDateTime date) {
        this.title = title;
        this.payer = payer;
        this.payee = payee;
        this.group = group;
        this.amount = amount;
        this.date = date;
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

    public void setPayer(User payer) {
        this.payer = payer;
    }

    public User getPayee() {
        return payee;
    }

    public void setPayee(User payee) {
        this.payee = payee;
    }

    public Optional<Group> getGroup() {
        return Optional.ofNullable(group);
    }

    public void setGroup(Group group) {
        this.group = group;
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
