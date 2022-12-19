package pl.age.edu.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.api.expense.dto.CreateGroupExpenseDTO;
import pl.age.edu.api.expense.ExpenseApi;
import pl.age.edu.api.group.GroupApi;
import pl.age.edu.api.user.UserApi;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.Expense;
import pl.age.edu.models.Group;
import pl.age.edu.models.User;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreateGroupExpenseViewController {
    @FXML
    private InputField titleInput;

    @FXML
    private InputField amountInput;

    @FXML
    private ListView<User> payerSelectList;

    @FXML
    private ListView<Group> groupSelectList;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        String payer = payerSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        String group = String.valueOf(groupSelectList.getSelectionModel().getSelectedItems().get(0).getId());
        CreateGroupExpenseDTO dto = new CreateGroupExpenseDTO(title, payer, group, amount);
        ExpenseApi.createGroupExpense(dto);
    }

    @FXML
    public void initialize() {
        List<User> allUsers = UserApi.getAll();
        ObservableList<User> users = FXCollections.observableArrayList(allUsers);
        payerSelectList.setItems(users);
        payerSelectList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(user.getFirstName() + " " + user.getLastName());
                }
            }});
        payerSelectList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        List<Group> allGroups = GroupApi.getAll();
        ObservableList<Group> groups = FXCollections.observableArrayList(allGroups);
        groupSelectList.setItems(groups);
        groupSelectList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Group group, boolean empty) {
                super.updateItem(group, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(group.toString());
                }
            }});
        groupSelectList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
