package pl.edu.agh.debtexecutor.controllers.views.forms.createCategory;

import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controllers.views.forms.FormViewController;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.services.CategoryService;
import pl.edu.agh.debtexecutor.utils.DialogUtils;

import java.util.Optional;

@Controller
public class CreateCategoryViewController extends FormViewController {
    private static final String FORM_NAME = "Create category";

    @FXML private InputField categoryName;

    @Autowired private CategoryService categoryService;

    @Override
    protected boolean validateForm() {
        if (categoryName.getText().isEmpty()) {
            DialogUtils.informationDialog(
                FORM_NAME,
                "Missing data in form",
                "\t- category name is not specified"
            );
            return false;
        }
        return true;
    }

    @Override
    protected boolean showConfirmationModal() {
        Optional<ButtonType> buttonType = DialogUtils.confirmationDialog(
                FORM_NAME,
                String.format("Check if category name is correct: %s", categoryName.getText())
        );

        return buttonType.map(type -> type.getButtonData().isDefaultButton())
                         .orElse(false);
    }

    @Override
    protected boolean submitForm() {
        return categoryService.addCategory(categoryName.getText()).isPresent();
    }

    @Override
    protected void clearInputs() {
        categoryName.clear();
    }

    @Override
    protected void handleSubmitSuccess() {
        DialogUtils.informationDialog(
            FORM_NAME,
            "Success!",
            "Category was successfully created"
        );
    }
}
