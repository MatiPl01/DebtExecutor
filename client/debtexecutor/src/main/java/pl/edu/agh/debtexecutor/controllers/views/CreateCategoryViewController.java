package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.services.CategoryService;

@Controller
public class CreateCategoryViewController extends FormViewController {
    @FXML private InputField categoryName;

    @Autowired private CategoryService categoryService;

    @FXML
    private void onSubmit() {
        String name = categoryName.getText();
        categoryService.addCategory(name);
        clearInputs();

        // TODO - add info modal that category was created
    }

    @Override
    protected void clearInputs() {
        categoryName.setText("");
    }
}
