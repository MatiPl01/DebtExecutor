package pl.edu.agh.debtexecutor.models;

import java.math.BigDecimal;

public class Expense {
    private final String title;
    private final ExpenseUserDTO payer;
    private final ExpenseUserDTO payee;
    private final ExpenseGroupDTO group;
    private final BigDecimal amount;
    private final String date;

    public Expense(String title,
                   ExpenseUserDTO payer,
                   ExpenseUserDTO payee,
                   ExpenseGroupDTO group,
                   BigDecimal amount,
                   String date) {
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

    public ExpenseUserDTO getPayer() {
        return payer;
    }

    public ExpenseUserDTO getPayee() {
        return payee;
    }

    public ExpenseGroupDTO getGroup() {
        return group;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    public static class ExpenseUserDTO {
        String firstName;
        String lastName;

        @Override
        public String toString() {
            return firstName  + " " + lastName;
        }
    }

    public static class ExpenseGroupDTO {
        String name;

        String placeholderName;

        @Override
        public String toString() {
            return (name != null && name.length() > 0) ? name : placeholderName;
        }

        public String getName() {
            return name;
        }

        public String getPlaceholderName() {
            return placeholderName;
        }
    }
}
