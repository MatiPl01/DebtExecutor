package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.models.Group;
import pl.edu.agh.debtexecutor.services.GroupService;
import pl.edu.agh.debtexecutor.services.UserService;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreateGroupExpenseViewController extends FormViewController {
    @FXML private InputField titleInput;
    @FXML private InputField amountInput;
    @FXML private ListView<User> payerSelectList;
    @FXML private ListView<Group> groupSelectList;

    @Autowired private UserService userService;
    @Autowired private GroupService groupService;
    @Autowired private ExpenseService expenseService;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        String payer = payerSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        String group = String.valueOf(groupSelectList.getSelectionModel().getSelectedItems().get(0).getId());
        expenseService.addGroupExpense(title, amount, payer, group);
        // TODO - add info modal that expense was created
    }

    @FXML
    public void initialize() {
        userService.fetchData();
        groupService.fetchData();
        List<User> users = userService.getUsers();
        List<Group> groups = groupService.getGroups();
        setListEntries(payerSelectList, users, SelectionMode.SINGLE);
        setListEntries(groupSelectList, groups, SelectionMode.SINGLE);
    }
}
