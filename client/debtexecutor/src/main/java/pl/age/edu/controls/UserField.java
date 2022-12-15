package pl.age.edu.controls;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pl.age.edu.models.User;

import java.io.IOException;

public class UserField extends HBox {
    private static final String FXML_PATH = "/fxml/controls/UserField.fxml";

    @FXML
    private Label userLabel;

    public UserField(User user) {
        // TODO - improve FXML loading

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(FXML_PATH));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // TODO - set labels based on group props
        userLabel.setText(user.getFirstName() + " " + user.getLastName());
    }
}
