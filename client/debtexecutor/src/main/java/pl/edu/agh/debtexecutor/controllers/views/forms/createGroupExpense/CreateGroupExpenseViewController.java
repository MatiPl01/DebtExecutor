package pl.edu.agh.debtexecutor.controllers.views.forms.createGroupExpense;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controllers.views.forms.FormViewController;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.Group;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.CategoryService;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.GroupService;
import pl.edu.agh.debtexecutor.services.UserService;
import pl.edu.agh.debtexecutor.utils.DialogUtils;
import pl.edu.agh.debtexecutor.utils.NumberUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Controller
public class CreateGroupExpenseViewController extends FormViewController {
    private static final String FORM_NAME = "Create group expense";

    @FXML private InputField titleInput;
    @FXML private InputField amountInput;
    @FXML private ListView<User> payerSelectList;
    @FXML private ListView<Group> groupSelectList;
    @FXML private ListView<Category> categorySelectList;

    @Autowired private UserService userService;
    @Autowired private GroupService groupService;
    @Autowired private CategoryService categoryService;
    @Autowired private ExpenseService expenseService;

    @FXML
    public void initialize() {
        userService.fetchData();
        groupService.fetchData();
        categoryService.fetchData();
        List<User> users = userService.getUsers();
        List<Group> groups = groupService.getGroups();
        List<Category> categories = categoryService.getCategories();
        setListEntries(payerSelectList, users, SelectionMode.SINGLE);
        setListEntries(groupSelectList, groups, SelectionMode.SINGLE);
        setListEntries(categorySelectList, categories, SelectionMode.SINGLE);
    }

    @Override
    protected boolean validateForm() {
        GroupExpenseFormData data = getFormData();

        StringJoiner joiner = new StringJoiner("\n\t- ");
        joiner.add("");
        if (data.title().isEmpty()) joiner.add("title cannot be empty");
        if (data.amount().isEmpty() || !NumberUtils.isPositive(data.amount())) {
            joiner.add("amount must be a positive number");
        }
        if (data.payer().isEmpty()) joiner.add("payer is not selected");
        if (data.group().isEmpty()) joiner.add("group is not selected");
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
        GroupExpenseFormData data = getFormData();

        StringJoiner joiner = new StringJoiner("\n");
        joiner.add("Check if the following details are correct");
        joiner.add("Title: " + data.title());
        joiner.add("Amount: " + data.amount());
        joiner.add("Payer: " + data.payer().get());
        joiner.add("Group: " + data.group().get());
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
        GroupExpenseFormData data = getFormData();

        return expenseService.addGroupExpense(
            data.title(),
            new BigDecimal(data.amount()),
            data.payer().get().getId(),
            data.group().get().getId(),
            data.category().get().getId()
        ).isPresent();
    }

    @Override
    protected void clearInputs() {
        titleInput.clear();
        amountInput.clear();
        payerSelectList.getSelectionModel().clearSelection();
        groupSelectList.getSelectionModel().clearSelection();
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

    private GroupExpenseFormData getFormData() {
        return new GroupExpenseFormData(
            titleInput.getText(),
            amountInput.getText(),
            Optional.ofNullable(payerSelectList.getSelectionModel().getSelectedItem()),
            Optional.ofNullable(groupSelectList.getSelectionModel().getSelectedItem()),
            Optional.ofNullable(categorySelectList.getSelectionModel().getSelectedItem())
        );
    }
}
