package pl.edu.agh.debtexecutor.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.math.BigDecimal;

public class SummaryItemBalance extends HBox {
    private static final String FXML_PATH =
            "/fxml/controls/SummaryItemBalance.fxml";
    private static final String NEGATIVE_AMOUNT_CLASS = "negative";

    @FXML private Label userLabel;
    @FXML private Label owesLabel;
    @FXML private Label amountLabel;

    public SummaryItemBalance(String userName, BigDecimal amount) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        boolean isPayee = amount.compareTo(BigDecimal.ZERO) > 0;
        userLabel.setText(userName);
        amountLabel.setText(amount.toString());

        if (isPayee) {
            owesLabel.setText("owes you");
        } else {
            owesLabel.setText("you owe");
            amountLabel.getStyleClass().add(NEGATIVE_AMOUNT_CLASS);
        }
    }
}
