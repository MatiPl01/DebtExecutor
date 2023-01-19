package pl.edu.agh.debtexecutor.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HistoryItem extends VBox {
    private static final String HIDDEN_CLASS_NAME = "hidden";
    private static final String FXML_PATH = "/fxml/controls/HistoryItem.fxml";

    @FXML private Label expenseTitle;
    @FXML private Label payerName;
    @FXML private Label payeeName;
    @FXML private Label groupName;
    @FXML private Label expenseAmount;
    @FXML private Label expenseDate;
    @FXML private Label category;

    public HistoryItem(Expense expense) {
        ResourceLoader.loadControlFXML(FXML_PATH, this);

        expenseTitle.setText(expense.getTitle());
        payerName.setText(expense.getPayer().toString());
        payeeName.setText(expense.getPayee().toString());
        if (expense.getGroup().isPresent()) groupName.setText(expense.getGroup().get().toString());
        else groupName.getStyleClass().add(HIDDEN_CLASS_NAME);
        expenseAmount.setText(expense.getAmount().toString());
        expenseDate.setText(formatDate(expense.getDate()));
        category.setText(expense.getCategory().getName());
    }

    private String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
