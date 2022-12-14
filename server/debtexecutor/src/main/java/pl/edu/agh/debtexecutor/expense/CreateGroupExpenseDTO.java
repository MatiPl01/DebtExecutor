package pl.edu.agh.debtexecutor.expense;

import java.math.BigDecimal;
import java.util.UUID;

public record CreateGroupExpenseDTO(String title,
                                    UUID payer,
                                    UUID group,
                                    BigDecimal amount) { }
