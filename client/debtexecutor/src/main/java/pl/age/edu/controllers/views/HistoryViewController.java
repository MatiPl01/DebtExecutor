package pl.age.edu.controllers.views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.age.edu.controls.HistoryItem;
import pl.age.edu.models.Expense;
import pl.age.edu.state.ExpenseState;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class HistoryViewController implements Initializable {
    @FXML
    private VBox historyWrapper;

    @Autowired
    private ExpenseState expenseState;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        expenseState.fetchData();
        ObservableList<Expense> expenses = expenseState.getExpenses();
        historyWrapper.getChildren().setAll(expenses.stream().map(HistoryItem::new).toList());
    }
}
