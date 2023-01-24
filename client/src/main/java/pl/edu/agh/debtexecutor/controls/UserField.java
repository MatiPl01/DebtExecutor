package pl.edu.agh.debtexecutor.controls;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

public class UserField extends HBox {
    private static final String FXML_PATH = "/fxml/controls/UserField.fxml";

    @FXML private Label userLabel;

    public UserField(User user) {
        ResourceLoader.loadControlFXML(FXML_PATH, this);

        userLabel.setText(user.getFirstName() + " " + user.getLastName());
    }
}
