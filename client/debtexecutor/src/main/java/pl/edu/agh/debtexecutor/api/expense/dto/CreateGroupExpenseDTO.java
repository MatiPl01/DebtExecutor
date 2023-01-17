package pl.edu.agh.debtexecutor.api.expense.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateGroupExpenseDTO(
        String title,
        UUID payer,
        UUID group,
        UUID category,
        BigDecimal amount) {}
