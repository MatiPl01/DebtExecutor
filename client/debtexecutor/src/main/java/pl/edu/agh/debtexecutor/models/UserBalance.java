package pl.edu.agh.debtexecutor.models;

import java.math.BigDecimal;
import java.util.UUID;

public class UserBalance {
    UUID id;
    String firstName;
    String lastName;
    BigDecimal balance;

    public UserBalance(UUID id, String firstName, String lastName, BigDecimal balance) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.balance = balance;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
