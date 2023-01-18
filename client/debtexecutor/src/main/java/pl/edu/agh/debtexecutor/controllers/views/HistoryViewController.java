package pl.edu.agh.debtexecutor.controllers.views;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controls.HistoryItem;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.models.Expense;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class HistoryViewController implements Initializable {
    @FXML private VBox historyWrapper;

    @Autowired private ExpenseService expenseService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        expenseService.fetchData();
        ObservableList<Expense> expenses = expenseService.getDisplayedExpenses();
        historyWrapper.getChildren().setAll(expenses.stream().map(HistoryItem::new).toList());
    }
}
