package pl.edu.agh.debtexecutor.controllers.views;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.agh.debtexecutor.controls.HistoryItem;
import pl.edu.agh.debtexecutor.controls.Pagination;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.options.PaginationOptions;
import pl.edu.agh.debtexecutor.services.utils.PaginationService;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class HistoryViewController implements Initializable {
    @FXML
    private HBox topPanel;
    @FXML
    private VBox historyWrapper;
    @FXML
    private Pagination pagination;

    @Autowired
    private ExpenseService expenseService;
    private PaginationService paginationService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        expenseService.fetchData();
        initializePaginationService();
        addExpenseHistoryChangeListener();
    }

    private void initializePaginationService() {
        paginationService = new PaginationService(expenseService);
        pagination.setPaginationService(paginationService);
        PaginationOptions options = paginationService.getPaginationOptions();

        pagination.currentPageNumberProperty()
                  .bind(options.pageNumberProperty().asString());
        pagination.totalPagesNumberProperty()
                  .bind(options.totalPagesProperty().asString());
    }

    private void addExpenseHistoryChangeListener() {
        expenseService.getDisplayedExpenses()
                      .addListener((ListChangeListener<Expense>) change ->
                              historyWrapper.getChildren().setAll(
                                      expenseService.getDisplayedExpenses()
                                                    .stream()
                                                    .map(HistoryItem::new)
                                                    .toList()
                              ));
    }
}
