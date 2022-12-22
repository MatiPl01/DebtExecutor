package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.UserService;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreatePersonalExpenseViewController extends FormViewController {
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
        expenseService.addPersonalExpense(title, amount, payer, payee);
        // TODO - add info modal that expense was created
    }

    @FXML
    public void initialize() {
        userService.fetchData();
        List<User> users = userService.getUsers();
        setListEntries(payerSelectList, users, SelectionMode.SINGLE);
        setListEntries(payeeSelectList, users, SelectionMode.SINGLE);
    }
}
