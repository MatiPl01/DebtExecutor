package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.Group;
import pl.age.edu.models.User;
import pl.age.edu.state.ExpenseState;
import pl.age.edu.state.GroupState;
import pl.age.edu.state.UserState;

import java.math.BigDecimal;

@Component
public class CreateGroupExpenseViewController extends FormViewController {
    @FXML
    private InputField titleInput;

    @FXML
    private InputField amountInput;

    @FXML
    private ListView<User> payerSelectList;

    @FXML
    private ListView<Group> groupSelectList;

    @Autowired
    private UserState userState;

    @Autowired
    private GroupState groupState;

    @Autowired
    private ExpenseState expenseState;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        String payer = payerSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        String group = String.valueOf(groupSelectList.getSelectionModel().getSelectedItems().get(0).getId());
        expenseState.addGroupExpense(title, amount, payer, group);
        // TODO - add info modal that expense was created
    }

    @FXML
    public void initialize() {
        userState.fetchData();
        groupState.fetchData();
        setListEntries(payerSelectList, userState.getUsers());
        setListEntries(groupSelectList, groupState.getGroups());
    }
}
