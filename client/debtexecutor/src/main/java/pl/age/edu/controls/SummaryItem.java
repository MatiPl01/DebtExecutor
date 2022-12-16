package pl.age.edu.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.age.edu.models.User;
import pl.age.edu.models.UserBalance;

import java.io.IOException;
import java.math.BigDecimal;

public class SummaryItem extends VBox {
    private static final String FXML_PATH = "/fxml/controls/SummaryItem.fxml";
    private static final String NEGATIVE_AMOUNT_CLASS = "negative";

    @FXML
    private Label userLabel;

    @FXML
    private Label totalBalanceLabel;

    @FXML
    private VBox detailsWrapper;

    public SummaryItem(User user) {
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        BigDecimal total = user.getBalance()
                               .stream()
                               .map(UserBalance::getBalance)
                               .reduce(BigDecimal.ZERO, BigDecimal::add);
        totalBalanceLabel.setText(total.toString());
        if (total.compareTo(BigDecimal.ZERO) < 0) totalBalanceLabel.getStyleClass().add(NEGATIVE_AMOUNT_CLASS);

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
