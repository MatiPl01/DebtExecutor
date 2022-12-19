package pl.age.edu.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.age.edu.models.Expense;

import java.io.IOException;

public class HistoryItem extends VBox {
    private static final String HIDDEN_CLASS_NAME = "hidden";
    private static final String FXML_PATH = "/fxml/controls/HistoryItem.fxml";

    @FXML
    private Label expenseTitle;

    @FXML
    private Label payerName;

    @FXML
    private Label payeeName;

    @FXML
    private Label groupName;

    @FXML
    private Label expenseAmount;

    @FXML
    private Label expenseDate;

    public HistoryItem(Expense expense) {
        // TODO - improve FXML loading

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        expenseTitle.setText(expense.getTitle());
        payerName.setText(expense.getPayer().toString());
        payeeName.setText(expense.getPayee().toString());
        if (expense.getGroup() != null) groupName.setText(expense.getGroup().toString());
        else groupName.getStyleClass().add(HIDDEN_CLASS_NAME);
        expenseAmount.setText(expense.getAmount().toString());
        expenseDate.setText(expense.getDate());
    }
}
