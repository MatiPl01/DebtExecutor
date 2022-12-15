package pl.age.edu.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import pl.age.edu.models.User;

import java.io.IOException;

public class SummaryItem extends VBox {
    private static final String FXML_PATH = "/fxml/controls/SummaryItem.fxml";

    @FXML
    private Label userLabel;

    @FXML
    private VBox balancesWrapper;

    public SummaryItem(User user) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        userLabel.setText(user.getFirstName() + " " + user.getLastName() + " balance:");

        user.getBalance().forEach(balance ->
                balancesWrapper
                        .getChildren()
                        .add(new Label(balance.getFirstName() + " " + balance.getLastName() + ": " + balance.getBalance().toString())));

    }
}
