package pl.edu.agh.debtexecutor.controllers.views;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pl.edu.agh.debtexecutor.controls.InputField;
import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.User;
import pl.edu.agh.debtexecutor.services.CategoryService;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.models.Group;
import pl.edu.agh.debtexecutor.services.GroupService;
import pl.edu.agh.debtexecutor.services.UserService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Controller
public class CreateGroupExpenseViewController extends FormViewController {
    @FXML private InputField titleInput;
    @FXML private InputField amountInput;
    @FXML private ListView<User> payerSelectList;
    @FXML private ListView<Group> groupSelectList;
    @FXML private ListView<Category> categorySelectList;

    @Autowired private UserService userService;
    @Autowired private GroupService groupService;
    @Autowired private CategoryService categoryService;
    @Autowired private ExpenseService expenseService;

    @FXML
    private void onSubmit() {
        String title = titleInput.getText();
        BigDecimal amount = new BigDecimal(amountInput.getText());
        UUID payerId = payerSelectList.getSelectionModel().getSelectedItem().getId();
        UUID groupId = groupSelectList.getSelectionModel().getSelectedItem().getId();
        UUID categoryId = categorySelectList.getSelectionModel().getSelectedItem().getId();
        expenseService.addGroupExpense(title, amount, payerId, groupId, categoryId);
        clearInputs();

        // TODO - add info modal that expense was created
    }

    @FXML
    public void initialize() {
        userService.fetchData();
        groupService.fetchData();
        categoryService.fetchData();
        List<User> users = userService.getUsers();
        List<Group> groups = groupService.getGroups();
        List<Category> categories = categoryService.getCategories();
        setListEntries(payerSelectList, users, SelectionMode.SINGLE);
        setListEntries(groupSelectList, groups, SelectionMode.SINGLE);
        setListEntries(categorySelectList, categories, SelectionMode.SINGLE);
    }

    @Override
    protected void clearInputs() {
        titleInput.setText("");
        amountInput.setText("");
        payerSelectList.getSelectionModel().clearSelection();
        groupSelectList.getSelectionModel().clearSelection();
        categorySelectList.getSelectionModel().clearSelection();
    }
}
