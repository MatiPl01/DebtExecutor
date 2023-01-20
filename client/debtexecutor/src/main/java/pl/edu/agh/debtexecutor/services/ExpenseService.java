package pl.edu.agh.debtexecutor.services;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;
import pl.edu.agh.debtexecutor.api.expense.ExpenseApi;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.CreateGroupExpenseDTO;
import pl.edu.agh.debtexecutor.api.expense.dto.GetExpensesResponseDTO;
import pl.edu.agh.debtexecutor.models.Category;
import pl.edu.agh.debtexecutor.models.Expense;
import pl.edu.agh.debtexecutor.services.options.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseService implements Paginable, Sortable, Filterable {
    private final static int DEFAULT_PAGE_SIZE = 10;

    private final SortOptions sortOptions = new SortOptions();
    private final FilterOptions filterOptions = new FilterOptions(List.of("category"));
    private final PaginationOptions paginationOptions =
            new PaginationOptions(DEFAULT_PAGE_SIZE);

    private final ObservableList<Expense> displayedExpenses =
            FXCollections.observableArrayList();

    private final ExpenseApi expenseApi;
    private final CategoryService categoryService;

    private ExpenseService(ExpenseApi expenseApi, CategoryService categoryService) {
        this.expenseApi = expenseApi;
        this.categoryService = categoryService;
        initializeFilterHandler();
    }

    @Override
    public SortOptions getSortOptions() {
        return sortOptions;
    }

    @Override
    public FilterOptions getFilterOptions() {
        return filterOptions;
    }

    @Override
    public PaginationOptions getPaginationOptions() {
        return paginationOptions;
    }

    @Override
    public void updateSorting() {
        fetchFilteredData();
    }

    @Override
    public void updateFilters() {
        paginationOptions.setPageNumber(1);
        fetchFilteredData();
    }

    @Override
    public void updatePagination() {
        fetchFilteredData();
    }

    public void fetchFilteredData() {
        Optional<GetExpensesResponseDTO> response = expenseApi.getExpenses(
                paginationOptions.getPageSize(),
                paginationOptions.getPageNumber(),
                sortOptions.getSortBy(),
                sortOptions.getSortDirection(),
                filterOptions.getAppliedFilterValues("category")
        );
        if (response.isPresent()) {
            displayedExpenses.setAll(response.get().content);
            paginationOptions.setTotalPages(response.get().totalPages);
        }
    }

    public ObservableList<Expense> getDisplayedExpenses() {
        return displayedExpenses;
    }

    public Optional<Expense> addPersonalExpense(String title,
                                                BigDecimal amount,
                                                UUID payerId,
                                                UUID payeeId,
                                                UUID categoryId) {
        CreateExpenseDTO dto = new CreateExpenseDTO(title,
                                                    payerId,
                                                    payeeId,
                                                    categoryId,
                                                    amount
        );
        return expenseApi.createPersonalExpense(dto);
    }

    public List<Expense> addGroupExpense(String title,
                                         BigDecimal amount,
                                         UUID payerId,
                                         UUID groupId,
                                         UUID categoryId) {
        CreateGroupExpenseDTO dto = new CreateGroupExpenseDTO(title,
                                                              payerId,
                                                              groupId,
                                                              categoryId,
                                                              amount
        );
        return expenseApi.createGroupExpense(dto);
    }

    public void clearFetchSettings() {
        sortOptions.clear();
        filterOptions.clear();
        paginationOptions.clear();
    }

    private void initializeFilterHandler() {
        categoryService.getCategories().addListener((ListChangeListener<Category>) change -> {
            filterOptions.setAvailableFilter(
                    "category",
                    categoryService.getCategories().stream().map(Category::getName).toList());
        });
    }
}
