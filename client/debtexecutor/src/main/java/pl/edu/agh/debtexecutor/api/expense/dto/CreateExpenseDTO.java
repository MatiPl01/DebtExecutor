package pl.edu.agh.debtexecutor.api.expense.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateExpenseDTO(
        String title,
        UUID payer,
        UUID payee,
        UUID category,
        BigDecimal amount
) {}
