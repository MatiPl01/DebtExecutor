package pl.edu.agh.debtexecutor.api.expense.dto;

import java.math.BigDecimal;

public record CreateExpenseDTO(
        String title,
        String payer,
        String payee,
        BigDecimal amount) {
}
