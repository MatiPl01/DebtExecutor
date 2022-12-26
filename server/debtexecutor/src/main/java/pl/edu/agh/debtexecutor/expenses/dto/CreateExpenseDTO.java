package pl.edu.agh.debtexecutor.expenses.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record CreateExpenseDTO(
        String title,
        UUID payer,
        UUID payee,
        BigDecimal amount,
        List<UUID> categories
) {
}
