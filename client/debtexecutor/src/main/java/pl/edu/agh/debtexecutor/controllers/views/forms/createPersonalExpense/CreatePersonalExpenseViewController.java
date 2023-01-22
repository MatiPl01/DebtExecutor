package pl.edu.agh.debtexecutor.controllers.views.forms.createPersonalExpense;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controllers.views.forms.FormViewController;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.CategoryService;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.UserService;
import pl.edu.agh.debtexecutor.utils.DialogUtils;
import pl.edu.agh.debtexecutor.utils.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

// TODO - extract similar code to the abstract class
@Controller
public class CreatePersonalExpenseViewController extends FormViewController {
    private final static String FORM_NAME = "Create personal expense";

    @FXML private InputField titleInput;
    @FXML private InputField amountInput;
    @FXML private ListView<User> payerSelectList;
    @FXML private ListView<User> payeeSelectList;
    @FXML private ListView<Category> categorySelectList;

    @Autowired private UserService userService;
    @Autowired private CategoryService categoryService;
    @Autowired private ExpenseService expenseService;

    @FXML
    public void initialize() {
        userService.fetchData();
        categoryService.fetchData();
        List<User> users = userService.getUsers();
        List<Category> categories = categoryService.getCategories();
        setListEntries(payerSelectList, users, SelectionMode.SINGLE);
        setListEntries(payeeSelectList, users, SelectionMode.SINGLE);
        setListEntries(categorySelectList, categories, SelectionMode.SINGLE);
    }

    @Override
    protected boolean validateForm() {
        PersonalExpenseFormData data = getFormData();

        StringJoiner joiner = new StringJoiner("\n\t- ");
        joiner.add("");
        if (data.title().isEmpty()) joiner.add("title cannot be empty");
        if (data.amount().isEmpty() || !NumberUtils.isPositive(data.amount())) {
            joiner.add("amount must be a positive number");
        }
        if (data.payer().isEmpty()) joiner.add("payer is not selected");
        if (data.payee().isEmpty()) joiner.add("payee is not selected");
        if (data.category().isEmpty()) joiner.add("category is not selected");

        if (joiner.length() > 0) {
            DialogUtils.informationDialog(
                    FORM_NAME,
                    "Missing data in form",
                    joiner.toString()
            );
            return false;
        }

        return true;
    }

    @Override
    protected boolean showConfirmationModal() {
        PersonalExpenseFormData data = getFormData();

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Check if the following details are correct");
        joiner.add("Title: " + data.title());
        joiner.add("Amount: " + data.amount());
        joiner.add("Payer: " + data.payer().get());
        joiner.add("Payee: " + data.payee().get());
        joiner.add("Category: " + data.category().get());

        Optional<ButtonType> buttonType = DialogUtils.confirmationDialog(
                FORM_NAME,
                joiner.toString()
        );

        return buttonType.map(type -> type.getButtonData().isDefaultButton())
                         .orElse(false);
    }

    @Override
    protected boolean submitForm() {
        PersonalExpenseFormData data = getFormData();

        return expenseService.addPersonalExpense(
                data.title(),
                new BigDecimal(data.amount()),
                data.payer().get().getId(),
                data.payee().get().getId(),
                data.category().get().getId()
        ).isPresent();
    }

    @Override
    protected void clearInputs() {
        titleInput.clear();
        amountInput.clear();
        payerSelectList.getSelectionModel().clearSelection();
        payeeSelectList.getSelectionModel().clearSelection();
        categorySelectList.getSelectionModel().clearSelection();
    }

    @Override
    protected void handleSubmitSuccess() {
        DialogUtils.informationDialog(
            FORM_NAME,
            "Success!",
            "Expense has been successfully added"
        );
    }

    private PersonalExpenseFormData getFormData() {
        return new PersonalExpenseFormData(
            titleInput.getText(),
            amountInput.getText(),
            Optional.ofNullable(payerSelectList.getSelectionModel().getSelectedItem()),
            Optional.ofNullable(payeeSelectList.getSelectionModel().getSelectedItem()),
            Optional.ofNullable(categorySelectList.getSelectionModel().getSelectedItem())
        );
    }
}
