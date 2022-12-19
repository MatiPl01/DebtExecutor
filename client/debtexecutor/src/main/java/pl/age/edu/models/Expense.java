package pl.age.edu.models;

import java.math.BigDecimal;

public class Expense {
    private String title;
    private ExpenseUserDTO payer;
    private ExpenseUserDTO payee;
    private ExpenseGroupDTO group;
    private BigDecimal amount;
    private String date;

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
