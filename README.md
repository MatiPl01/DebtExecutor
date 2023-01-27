# DebtExecutor

## :pencil: Description

This project was created for the Object-Oriented Technologies subject at the AGH UST in 2022/2023.

The project is a simple app that helps people to track personal or group expenses and calculate the amount that needs to be paid back.

## :bulb: Functionalities

### Users and groups

- creating a new user account,
- logging in to an existing user account,
- creating groups of users (used to create a group expense)

### Expenses

- displaying a history of all expenses,
- sorting expenses by title, date or amount,
- filtering expenses by categories,
- displaying a graph containing all expenses
- creating a new personal expense (one user paid for an another user),
- creating group expenses (one user paid for a group of users - group must have been created before),
- creating a new expense category

### Summaries

- displaying expense summaries for each user as a list,
- displaying expense summaries graph

### Simplification of expenses

- displaying a simplified expenses graph (this graph is a simplification of all expenses and represents the minimum number of payments required to pay all debts of all users)

## :hammer: Core technology stack

### Client

- Java 17,
- JavaFX,
- Spring Boot,
- Retrofit

### Server

- Java 17,
- Spring Boot

## :woman::man: Contributors

- [Ewa Miklewska](https://github.com/Ewa-M),
- [Jakub Stępień](https://github.com/jkbstepien)

## :eyes: Showcase

### Auth forms

#### Sign in form

| Light mode                           | Dark mode                           |
| ------------------------------------ | ----------------------------------- |
| ![Sign in](./docs/light/sign-in.png) | ![Sign in](./docs/dark/sign-in.png) |

#### Sign up form

| Light mode                           | Dark mode                           |
| ------------------------------------ | ----------------------------------- |
| ![Sign up](./docs/light/sign-up.png) | ![Sign up](./docs/dark/sign-up.png) |

### Views

#### Expense history view

##### Expense list

| Light mode                                        | Dark mode                                        |
| ------------------------------------------------- | ------------------------------------------------ |
| ![Expense history list](./docs/light/history.png) | ![Expense history list](./docs/dark/history.png) |

##### Expense filtering

| Light mode                                                          | Dark mode                                                          |
| ------------------------------------------------------------------- | ------------------------------------------------------------------ |
| ![Expense history list filtering](./docs/light/history-filters.png) | ![Expense history list filtering](./docs/dark/history-filters.png) |

##### Expense sort by

| Light mode                                                        | Dark mode                                                        |
| ----------------------------------------------------------------- | ---------------------------------------------------------------- |
| ![Expense history list sort by](./docs/light/history-sorting.png) | ![Expense history list sort by](./docs/dark/history-sorting.png) |

##### Expense sort direction

| Light mode                                                                      | Dark mode                                                                      |
| ------------------------------------------------------------------------------- | ------------------------------------------------------------------------------ |
| ![Expense history list sort direction](./docs/light/history-sort-direction.png) | ![Expense history list sort direction](./docs/dark/history-sort-direction.png) |

##### Expense pagination

| Light mode                                                              | Dark mode                                                              |
| ----------------------------------------------------------------------- | ---------------------------------------------------------------------- |
| ![Expense history list pagination](./docs/light/history-pagination.png) | ![Expense history list pagination](./docs/dark/history-pagination.png) |

##### Expense graph

| Light mode                                               | Dark mode                                               |
| -------------------------------------------------------- | ------------------------------------------------------- |
| ![Expense history graph](./docs/light/history-graph.png) | ![Expense history graph](./docs/dark/history-graph.png) |

#### Expense summary view

##### Summaries list

| Light mode                                  | Dark mode                                  |
| ------------------------------------------- | ------------------------------------------ |
| ![Summaries list](./docs/light/summary.png) | ![Summaries list](./docs/dark/summary.png) |

##### Summaries graph view

| Light mode                                         | Dark mode                                         |
| -------------------------------------------------- | ------------------------------------------------- |
| ![Summaries graph](./docs/light/summary-graph.png) | ![Summaries graph](./docs/dark/summary-graph.png) |

#### Simplified expenses graph view

| Light mode                                                               | Dark mode                                                               |
| ------------------------------------------------------------------------ | ----------------------------------------------------------------------- |
| ![Simplified expenses graph](./docs/light/simplified-expenses-graph.png) | ![Simplified expenses graph](./docs/dark/simplified-expenses-graph.png) |

### Forms

#### Create personal expense form

| Light mode                                                                | Dark mode                                                                |
| ------------------------------------------------------------------------- | ------------------------------------------------------------------------ |
| ![Create personal expense form](./docs/light/create-personal-expense.png) | ![Create personal expense form](./docs/dark/create-personal-expense.png) |

#### Create group expense form

| Light mode                                                          | Dark mode                                                          |
| ------------------------------------------------------------------- | ------------------------------------------------------------------ |
| ![Create group expense form](./docs/light/create-group-expense.png) | ![Create group expense form](./docs/dark/create-group-expense.png) |

#### Create category form

| Light mode                                                | Dark mode                                                |
| --------------------------------------------------------- | -------------------------------------------------------- |
| ![Create category form](./docs/light/create-category.png) | ![Create category form](./docs/dark/create-category.png) |

#### Create group form

| Light mode                                          | Dark mode                                          |
| --------------------------------------------------- | -------------------------------------------------- |
| ![Create group form](./docs/light/create-group.png) | ![Create group form](./docs/dark/create-group.png) |

### Others

#### User panel

| Light mode                                 | Dark mode                                 |
| ------------------------------------------ | ----------------------------------------- |
| ![User panel](./docs/light/user-panel.png) | ![User panel](./docs/dark/user-panel.png) |
