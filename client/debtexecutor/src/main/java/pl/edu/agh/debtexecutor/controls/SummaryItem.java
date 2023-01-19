package pl.edu.agh.debtexecutor.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

import java.math.BigDecimal;

public class SummaryItem extends VBox {
    private static final String FXML_PATH = "/fxml/controls/SummaryItem.fxml";
    private static final String NEGATIVE_AMOUNT_CLASS = "negative";

    @FXML private Label userLabel;
    @FXML private Label totalBalanceLabel;
    @FXML private VBox detailsWrapper;

    public SummaryItem(User user) {
        ResourceLoader.loadControlFXML(FXML_PATH, this);

        totalBalanceLabel.setText(user.getTotalBalance().toString());
        if (user.getTotalBalance().compareTo(BigDecimal.ZERO) < 0) {
            totalBalanceLabel.getStyleClass().add(NEGATIVE_AMOUNT_CLASS);
        }

        userLabel.setText(user.getFirstName() + " " + user.getLastName());
        user.getBalance().forEach(balance -> {
            detailsWrapper.getChildren().add(
                    new SummaryItemBalance(
                            balance.getFirstName() +
                            " " +
                            balance.getLastName(),
                            balance.getBalance()
                    ));
        });
    }
}
