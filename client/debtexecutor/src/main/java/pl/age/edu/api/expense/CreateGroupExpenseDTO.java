package pl.age.edu.api.expense;

import java.math.BigDecimal;

public record CreateGroupExpenseDTO(
        String title,
        String payer,
        String group,
        BigDecimal amount) {}
