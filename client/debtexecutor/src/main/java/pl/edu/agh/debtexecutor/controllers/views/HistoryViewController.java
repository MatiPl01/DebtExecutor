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
import pl.edu.agh.debtexecutor.controls.sorting.SortSettings;
import pl.edu.agh.debtexecutor.controls.sorting.SortValueType;
import pl.edu.agh.debtexecutor.controls.sorting.Sorting;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.utils.PaginationService;
import pl.edu.agh.debtexecutor.services.utils.SortingService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class HistoryViewController implements Initializable {
    private static final List<SortSettings> settings = new ArrayList<>() {{
        add(new SortSettings("Date", "date", SortValueType.ORDINAL));
        add(new SortSettings("Amount", "amount", SortValueType.ORDINAL));
        add(new SortSettings("Title", "title", SortValueType.LEXICAL));
    }};

    @FXML private HBox topPanel;
    @FXML private VBox historyWrapper;
    @FXML private Pagination pagination;
    @FXML private Sorting sorting;

    @Autowired
    private ExpenseService expenseService;
    private PaginationService paginationService;
    private SortingService sortingService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addExpenseHistoryChangeListener();
        expenseService.fetchData();
        initializePagination();
        initializeSorting();
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

    private void initializePagination() {
        paginationService = new PaginationService(expenseService);
        pagination.setPaginationService(paginationService);
    }

    private void initializeSorting() {
        sortingService = new SortingService(expenseService);
        sorting.setSortingService(sortingService);
        sorting.setSortSettings(settings);
    }
}
