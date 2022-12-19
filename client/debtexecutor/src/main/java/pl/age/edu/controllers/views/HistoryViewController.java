package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import pl.age.edu.api.expense.ExpenseApi;
import pl.age.edu.controls.HistoryItem;
import pl.age.edu.models.Expense;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HistoryViewController implements Initializable {
    @FXML
    private VBox historyWrapper;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Expense> expenses = ExpenseApi.getAll();

        expenses.forEach(expense -> historyWrapper.getChildren().add(new HistoryItem(expense)));
    }
}
