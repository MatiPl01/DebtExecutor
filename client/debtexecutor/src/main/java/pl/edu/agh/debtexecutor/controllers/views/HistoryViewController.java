package pl.edu.agh.debtexecutor.controllers.views;

import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import pl.edu.agh.debtexecutor.controls.HistoryItem;
import pl.edu.agh.debtexecutor.controls.Pagination;
import pl.edu.agh.debtexecutor.controls.multiSelect.MultiSelectCombobox;
import pl.edu.agh.debtexecutor.controls.multiSelect.MultiselectItem;
import pl.edu.agh.debtexecutor.controls.sorting.SortSettings;
import pl.edu.agh.debtexecutor.controls.sorting.SortValueType;
import pl.edu.agh.debtexecutor.controls.sorting.Sorting;
import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.services.CategoryService;
import pl.edu.agh.debtexecutor.services.ExpenseService;
import pl.edu.agh.debtexecutor.services.utils.FilterService;
import pl.edu.agh.debtexecutor.services.utils.PaginationService;
import pl.edu.agh.debtexecutor.services.utils.SortingService;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Controller
public class HistoryViewController extends HistoryViewAbstractController implements Initializable {
    private final static String FILTER_FIELD = "category";
    private static final List<SortSettings> settings = new ArrayList<>() {{
        add(new SortSettings("Date", "date", SortValueType.ORDINAL));
        add(new SortSettings("Amount", "amount", SortValueType.ORDINAL));
        add(new SortSettings("Title", "title", SortValueType.LEXICAL));
    }};

    @FXML private VBox historyWrapper;
    @FXML private Pagination pagination;
    @FXML private Sorting sorting;
    @FXML private MultiSelectCombobox categoryFilterCombobox;

    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private CategoryService categoryService;

    private PaginationService paginationService;
    private SortingService sortingService;
    private FilterService filterService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addExpenseHistoryChangeListener();
        expenseService.clearFetchSettings();
        expenseService.fetchFilteredData();
        categoryService.fetchData();
        initializePagination();
        initializeSorting();
        initializeFilters();
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

    private void initializeFilters() {
        filterService = new FilterService(expenseService);
        categoryFilterCombobox.setItems(createMultiselectItems(categoryService.getCategories()));
        categoryFilterCombobox.getSelectedValues().addListener(((ListChangeListener<String>) change -> {
            while (change.next()) {
                if (change.wasAdded()) {
                    filterService.addFilter(FILTER_FIELD, change.getAddedSubList().get(0));
                } else if (change.wasRemoved()) {
                    filterService.removeFilter(FILTER_FIELD, change.getRemoved().get(0));
                }
            }
        }));
        categoryFilterCombobox.onClear(() -> filterService.clearFilter(FILTER_FIELD));
    }

    private List<MultiselectItem> createMultiselectItems(List<Category> values) {
        return values.stream().map(c -> new MultiselectItem(
                StringUtils.capitalize(c.getName()), c.getName())
        ).toList();
    }
}
