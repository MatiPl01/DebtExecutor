package pl.edu.agh.debtexecutor.controllers.views.forms.createGroupExpense;

import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.Group;
import pl.edu.agh.debtexecutor.models.User;

import java.util.Optional;

public record GroupExpenseFormData(
    String title,
    String amount,
    Optional<User> payer,
    Optional<Group> group,
    Optional<Category> category
) {}
