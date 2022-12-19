package pl.age.edu.models;

import java.math.BigDecimal;

public class Expense {
    public String title;
    public ExpenseUserDTO payer;
    public ExpenseUserDTO payee;
    public ExpenseGroupDTO group;
    public BigDecimal amount;
    public String date;

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

    public class ExpenseUserDTO {
        String firstName;
        String lastName;

        @Override
        public String toString() {
            return firstName  + " " + lastName;
        }
    }

    public class ExpenseGroupDTO {
        String name;

        public String getName() {
            return name;
        }
    }
}
