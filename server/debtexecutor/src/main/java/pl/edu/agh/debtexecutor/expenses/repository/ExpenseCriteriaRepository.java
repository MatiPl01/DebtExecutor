package pl.edu.agh.debtexecutor.expenses.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;
import pl.edu.agh.debtexecutor.expenses.model.Expense;
import pl.edu.agh.debtexecutor.expenses.model.ExpensePage;
import pl.edu.agh.debtexecutor.expenses.model.ExpenseSearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ExpenseCriteriaRepository {
    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ExpenseCriteriaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Expense> findAllWithFilters(ExpensePage expensePage,
                                            ExpenseSearchCriteria searchCriteria) {
        CriteriaQuery<Expense> criteriaQuery =
                criteriaBuilder.createQuery(Expense.class);
        Root<Expense> expenseRoot = criteriaQuery.from(Expense.class);

        filterExpenses(criteriaQuery, searchCriteria, expenseRoot);
        sortExpenses(expensePage, criteriaQuery, expenseRoot);
        TypedQuery<Expense> query =
                paginateExpenses(expensePage, criteriaQuery);

        Pageable pageable = createPageable(expensePage);
        long expenseCount = getExpenseCount(searchCriteria);

        return new PageImpl<>(query.getResultList(), pageable, expenseCount);
    }

    private void filterExpenses(CriteriaQuery<Expense> criteriaQuery,
                                ExpenseSearchCriteria searchCriteria,
                                Root<Expense> root) {
        criteriaQuery.where(createPredicate(searchCriteria, root));
    }

    private Predicate createPredicate(ExpenseSearchCriteria searchCriteria,
                                   Root<Expense> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(searchCriteria.getCategories())) {
            Predicate[] categoryPredicates = searchCriteria
                    .getCategories()
                    .stream()
                    .map(category -> criteriaBuilder
                            .like(
                                    root.get("category").get("name"),
                                    category
                            ))
                    .toArray(Predicate[]::new);
            predicates.add(criteriaBuilder.or(categoryPredicates));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void sortExpenses(ExpensePage expensePage,
                              CriteriaQuery<Expense> criteriaQuery,
                              Root<Expense> expenseRoot) {
        Order order;
        Expression<Expense> sortBy = expenseRoot.get(expensePage.getSortBy());

        if (expensePage.getSortDirection().equals(Sort.Direction.ASC)) {
            order = criteriaBuilder.asc(sortBy);
        } else {
            order = criteriaBuilder.desc(sortBy);
        }

        criteriaQuery.orderBy(order);
    }

    private TypedQuery<Expense> paginateExpenses(ExpensePage expensePage,
                                                 CriteriaQuery<Expense> criteriaQuery) {
        TypedQuery<Expense> query = entityManager.createQuery(criteriaQuery);
        int firstIdx = expensePage.getPageNumber() * expensePage.getPageSize();
        query.setFirstResult(firstIdx);
        query.setMaxResults(expensePage.getPageSize());
        return query;
    }

    private Pageable createPageable(ExpensePage expensePage) {
        Sort sort = Sort.by(
                expensePage.getSortDirection(),
                expensePage.getSortBy()
        );
        return PageRequest.of(
                expensePage.getPageNumber(),
                expensePage.getPageSize(),
                sort
        );
    }

    private long getExpenseCount(ExpenseSearchCriteria searchCriteria) {
        CriteriaQuery<Long> countQuery =
                criteriaBuilder.createQuery(Long.class);
        Root<Expense> countRoot = countQuery.from(Expense.class);
        countQuery.select(criteriaBuilder.count(countRoot))
                  .where(createPredicate(searchCriteria, countRoot));
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
