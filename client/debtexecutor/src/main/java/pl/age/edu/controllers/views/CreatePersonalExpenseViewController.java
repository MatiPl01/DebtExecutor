package pl.age.edu.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.stereotype.Component;
import pl.age.edu.api.expense.ExpenseApi;
import pl.age.edu.api.expense.dto.CreateExpenseDTO;
import pl.age.edu.api.user.UserApi;
import pl.age.edu.controls.InputField;
import pl.age.edu.models.User;

import java.math.BigDecimal;
import java.util.List;

@Component
public class CreatePersonalExpenseViewController {
    @FXML
    private InputField titleInput;
    @FXML
    private InputField amountInput;

    @FXML
    private ListView<User> payerSelectList;
    @FXML
    private ListView<User> payeeSelectList;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        String payer = payerSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        String payee = payeeSelectList.getSelectionModel().getSelectedItems().get(0).getId();
        CreateExpenseDTO dto = new CreateExpenseDTO(title, payer, payee, amount);
        ExpenseApi.createPersonalExpense(dto);
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


        payeeSelectList.setItems(users);
        payeeSelectList.setCellFactory(param -> new ListCell<>() {
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
        payeeSelectList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }
}
