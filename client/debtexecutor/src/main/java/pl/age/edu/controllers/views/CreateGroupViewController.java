package pl.age.edu.controllers.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.SelectionMode;
import pl.age.edu.controls.InputField;
import javafx.scene.control.ListView;
import pl.age.edu.models.User;

import java.util.ArrayList;

public class CreateGroupViewController {
    @FXML
    private InputField groupNameInput;

    @FXML
    private ListView<User> userSelectList;

    @FXML
    private void onSubmit() {
        // TODO - add post request to create a new group
        System.out.println(groupNameInput.getText());
        System.out.println(userSelectList.getSelectionModel().getSelectedItems());
    }

    @FXML
    public void initialize() {
        // TODO - use some state management service
        ObservableList<User> users = FXCollections.observableArrayList();
        users.add(new User("0", "Mateusz", "Łopaciński", new ArrayList<>(), new ArrayList<>()));
        users.add(new User("1", "Ewa", "Miklewska", new ArrayList<>(), new ArrayList<>()));
        users.add(new User("2", "Jakub", "Stępień", new ArrayList<>(), new ArrayList<>()));

        userSelectList.setItems(users);
        userSelectList.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(User user, boolean empty) {
                super.updateItem(user, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(user.getFirstName() + " " + user.getLastName());
                }
            }});

        userSelectList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
