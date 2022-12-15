package pl.age.edu.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class Expense {
    String title;
    ExpenseUserDTO payer;
    ExpenseUserDTO payee;
    Optional<ExpenseGroupDTO> group;
    BigDecimal amount;
    LocalDateTime date;

    public String getTitle() {
        return title;
    }

    public ExpenseUserDTO getPayer() {
        return payer;
    }

    public ExpenseUserDTO getPayee() {
        return payee;
    }

    public Optional<ExpenseGroupDTO> getGroup() {
        return group;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    private class ExpenseUserDTO {
        String firstName;
        String lastName;
    }

    private class ExpenseGroupDTO {
        String name;
    }
}
