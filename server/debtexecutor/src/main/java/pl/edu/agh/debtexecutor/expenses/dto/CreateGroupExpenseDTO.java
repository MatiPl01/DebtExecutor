package pl.edu.agh.debtexecutor.expenses.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateGroupExpenseDTO(
        String title,
        UUID payer,
        UUID group,
        BigDecimal amount,
        UUID category
) {
}
