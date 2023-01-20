# DebtExecutor

## Changelog:

### Milestone 1
#### 1. Endpoints:
- add group
- get all groups
- get user
- get all users
- get all expenses
- add expense (personal)
- add expense (group)
#### 2. GUI:
- left side contains navigation panel:
   - adding users/groups/expenses
      - switching between expense history (all expenses) and expense summary (users balance).
      - all users list
      - all groups list
- right side displays content for chosen option from navigation panel.

### Milestone 2
#### 1. New endpoints:
- categories:
   - get all categories,
   - get category by id,
   - create a new category
- expenses (endpoints that were changed)
   - get all expenses,
   - get filtered, paginated and sorted expenses,
- graphs
   - get expense history graph,
   - get expense summary graph,
   - get expense simplified graph,
#### 2. GUI:
- sorting, filtering and pagination in the expense history tab,
- list/graph view switch to the history and summary tab,
- graph view displaying expense history and expense summary graph,
- simplified expenses graph tab (generating the simplified graph is broken and will be fixed soon)

## Requirements

- Postgresql database named "debtexecutor"
- Java 16 or higher

## Running the app

- server: DebtexecutorApplication.main
- client: Main.main

## Example views

[Open example views](./docs/)

## Authors

- Ewa Miklewska
- Jakub Stępień
- Mateusz Łopaciński
