package pl.edu.agh.debtexecutor.controls;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import pl.edu.agh.debtexecutor.models.Group;
import pl.edu.agh.debtexecutor.utils.ResourceLoader;

public class GroupField extends HBox {
    private static final String FXML_PATH = "/fxml/controls/GroupField.fxml";

    @FXML private Label membersCountLabel;
    @FXML private Label groupNameLabel;

    public GroupField(Group group) {
        ResourceLoader.loadControlFXML(FXML_PATH, this);

        membersCountLabel.setText(String.valueOf(group.getMembers().size()));
        groupNameLabel.setText(group.toString());
    }
}
