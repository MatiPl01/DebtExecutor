package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.CategoryService;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.UserService;
import pl.edu.agh.debtexecutor.utils.DialogUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
public class CreatePersonalExpenseViewController extends FormViewController {
    private final static String FORM_TITLE = "Create Personal Expense";

    @FXML private InputField titleInput;
    @FXML private InputField amountInput;
    @FXML private ListView<User> payerSelectList;
    @FXML private ListView<User> payeeSelectList;
    @FXML private ListView<Category> categorySelectList;

    @Autowired private UserService userService;
    @Autowired private CategoryService categoryService;
    @Autowired private ExpenseService expenseService;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        UUID payerId = payerSelectList.getSelectionModel().getSelectedItem().getId();
        UUID payeeId = payeeSelectList.getSelectionModel().getSelectedItem().getId();
        UUID categoryId = categorySelectList.getSelectionModel().getSelectedItem().getId();

        // TODO - add form validation
        Optional<ButtonType> buttonType = DialogUtils.confirmationDialog(
                FORM_TITLE,
                "Check if expense details are correct:\n"
        );
        if (buttonType.isEmpty() || buttonType.get() != ButtonType.OK) return;

        Optional<Expense> expense = expenseService.addPersonalExpense(title, amount, payerId, payeeId, categoryId);
        if (expense.isPresent()) {
            DialogUtils.informationDialog(FORM_TITLE, "Header", "Content");
        } else {
            DialogUtils.errorDialog(FORM_TITLE, "Error");
        }
        clearInputs();
    }

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
    protected void clearInputs() {
        titleInput.setText("");
        amountInput.setText("");
        payerSelectList.getSelectionModel().clearSelection();
        payeeSelectList.getSelectionModel().clearSelection();
        categorySelectList.getSelectionModel().clearSelection();
    }
}
