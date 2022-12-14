package pl.edu.agh.debtexecutor.expense;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateExpenseDTO(
        String title,
        UUID payer,
        UUID payee,
        BigDecimal amount) {
}
