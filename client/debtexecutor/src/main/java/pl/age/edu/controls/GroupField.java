package pl.age.edu.controls;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pl.age.edu.models.Group;

import java.io.IOException;

public class GroupField extends HBox {
    private static final String FXML_PATH = "/fxml/controls/GroupField.fxml";

    @FXML
    private Label membersCountLabel;

    @FXML
    private Label groupNameLabel;

    public GroupField(Group group) {
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
        membersCountLabel.setText(String.valueOf(5));
        groupNameLabel.setText(group.getName());
    }
}
