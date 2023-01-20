package pl.edu.agh.debtexecutor.models;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public final class Expense {
    private final UUID id;
    private final String title;
    private final ExpenseUserDTO payer;
    private final ExpenseUserDTO payee;
    private final BigDecimal amount;
    private final String date;
    private final ExpenseGroupDTO group;
    private final ExpenseCategoryDTO category;

    public Expense(UUID id,
                   String title,
                   ExpenseUserDTO payer,
                   ExpenseUserDTO payee,
                   ExpenseGroupDTO group,
                   ExpenseCategoryDTO category,
                   BigDecimal amount,
                   String date) {
        this.id = id;
        this.title = title;
        this.payer = payer;
        this.payee = payee;
        this.group = group;
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public UUID getId() {
        return id;
    }

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
        return Optional.ofNullable(group);
    }

    public ExpenseCategoryDTO getCategory() {
        return category;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getDate() {
        return LocalDateTime.parse(date);
    }

    public static class ExpenseUserDTO {
        String id;
        String firstName;
        String lastName;

        public String getId() {
            return id;
        }

        @Override
        public String toString() {
            return firstName  + " " + lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }
    }

    public static class ExpenseGroupDTO {
        String id;
        String name;
        String placeholderName;

        @Override
        public String toString() {
            return (name != null && name.length() > 0) ? name : placeholderName;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getPlaceholderName() {
            return placeholderName;
        }
    }

    public static class ExpenseCategoryDTO {
        String id;
        String name;

        public String getId() {
            return id;
        }

        public String getName() { return name; }
    }
}
