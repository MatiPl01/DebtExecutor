package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.UserService;
import pl.edu.agh.debtexecutor.utils.DialogUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class CreatePersonalExpenseViewController extends FormViewController {
    private final static String FORM_TITLE = "Create Personal Expense";

    @FXML private InputField titleInput;
    @FXML private InputField amountInput;
    @FXML private ListView<User> payerSelectList;
    @FXML private ListView<User> payeeSelectList;

    @Autowired private UserService userService;
    @Autowired private ExpenseService expenseService;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        String payer = payerSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        String payee = payeeSelectList.getSelectionModel().getSelectedItems().get(0).getId();

        // TODO - add form validation
        Optional<ButtonType> buttonType = DialogUtils.confirmationDialog(
                FORM_TITLE,
                "Check if expense details are correct:\n"
        );
        if (buttonType.isEmpty() || buttonType.get() != ButtonType.OK) return;

        Optional<Expense> expense = expenseService.addPersonalExpense(title, amount, payer, payee);
        if (expense.isPresent()) {
            DialogUtils.informationDialog(FORM_TITLE, "Header", "Content");
        } else {
            DialogUtils.errorDialog(FORM_TITLE, "Error");
        }
    }

    @FXML
    public void initialize() {
        userService.fetchData();
        List<User> users = userService.getUsers();
        setListEntries(payerSelectList, users, SelectionMode.SINGLE);
        setListEntries(payeeSelectList, users, SelectionMode.SINGLE);
    }
}
