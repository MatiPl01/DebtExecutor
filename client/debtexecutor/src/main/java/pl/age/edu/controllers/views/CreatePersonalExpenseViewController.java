package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.User;
import pl.age.edu.state.ExpenseState;
import pl.age.edu.state.UserState;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreatePersonalExpenseViewController extends FormViewController {
    @FXML
    private InputField titleInput;

    @FXML
    private InputField amountInput;

    @FXML
    private ListView<User> payerSelectList;

    @FXML
    private ListView<User> payeeSelectList;

    @Autowired
    private UserState userState;

    @Autowired
    private ExpenseState expenseState;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        String payer = payerSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        String payee = payeeSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        expenseState.addPersonalExpense(title, amount, payer, payee);
        // TODO - add info modal that expense was created
    }

    @FXML
    public void initialize() {
        userState.fetchData();
        List<User> users = userState.getUsers();
        setListEntries(payerSelectList, users);
        setListEntries(payeeSelectList, users);
    }
}
