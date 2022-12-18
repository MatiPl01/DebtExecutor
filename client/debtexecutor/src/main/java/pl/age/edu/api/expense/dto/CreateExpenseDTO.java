package pl.age.edu.api.expense.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateExpenseDTO(
        String title,
        String payer,
        String payee,
        BigDecimal amount) {
}
