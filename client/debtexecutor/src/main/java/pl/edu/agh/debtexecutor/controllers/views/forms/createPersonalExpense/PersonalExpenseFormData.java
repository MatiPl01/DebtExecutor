package pl.edu.agh.debtexecutor.controllers.views.forms.createPersonalExpense;

import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.User;

import java.util.Optional;

public record PersonalExpenseFormData(
    String title,
    String amount,
    Optional<User> payer,
    Optional<User> payee,
    Optional<Category> category
) {}
