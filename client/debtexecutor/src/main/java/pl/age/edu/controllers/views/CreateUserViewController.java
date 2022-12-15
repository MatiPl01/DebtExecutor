package pl.age.edu.controllers.views;

import javafx.fxml.FXML;
import pl.age.edu.api.user.CreateUserDTO;
import pl.age.edu.api.user.UserController;
import pl.age.edu.controls.InputField;

public class CreateUserViewController {

    @FXML
    private InputField firstNameInput;

    @FXML
    private InputField lastNameInput;

    @FXML
    private void onAddUser() {
        System.out.println(firstNameInput.getText());
        System.out.println(lastNameInput.getText());
        CreateUserDTO user = new CreateUserDTO(firstNameInput.getText(), lastNameInput.getText());
        UserController.add(user);
    }
}
