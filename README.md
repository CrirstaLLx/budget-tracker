# Budget Tracker

A personal finance tracker built with Java and Spring Boot: manage accounts, income and expense transactions, and monthly budgets per category.

> **Status:** work in progress. The domain logic and unit tests are done, and the first REST endpoints are running. Persistence, the remaining endpoints and the frontend are planned.

## Tech stack

- Java 21
- Spring Boot (REST API)
- Maven
- JUnit 5

**Planned:** PostgreSQL (Spring Data JPA), Angular

## What is implemented

- Domain model: `Account`, `Transaction`, `Budget`, `ExpenseCategory`, `TransactionType`
- `FinanceManager` service:
  - adding accounts, transactions and budgets
  - validation: a transaction can only be added to a known account
  - account balance calculation (initial balance + income - expenses)
  - monthly expenses grouped by category
  - budget status: percentage of a monthly category limit already spent
  - adding a budget replaces an existing one for the same category and month
- `BigDecimal` for all monetary values to avoid floating-point rounding errors
- REST API:
  - `GET /api/accounts`: list accounts
  - `POST /api/accounts`: create an account
- Unit tests (JUnit 5) for balance calculation, monthly category totals, budget status, budget replacement and validation errors

## Project structure

```
src/main/java/com/crirstallx/budgettracker
├── controller                  # REST controllers
├── model                       # data classes and enums
├── service                     # business logic (FinanceManager)
├── BudgetTrackerApplication    # Spring Boot entry point
└── Main.java                   # console demo
src/test/java                   # JUnit tests
```

## How to run

Requirements: JDK 21 (Maven is bundled with IntelliJ IDEA, or install it separately).

**REST API:** run `BudgetTrackerApplication`, then open `http://localhost:8080/api/accounts`.

Create an account:

```http
POST http://localhost:8080/api/accounts
Content-Type: application/json

{
  "accountName": "Main account",
  "balance": 1000.00
}
```

**Tests:** run `mvn test`, or run the `FinanceManagerTest` class from your IDE.

Data is stored in memory only, so it is lost when the application stops.

## Roadmap

- [x] Budget status per category and month
- [x] Replace duplicate budgets
- [x] Spring Boot setup, first REST endpoints (accounts)
- [ ] REST endpoints for transactions and budgets (account IDs, DTOs, validation)
- [ ] Monthly spending trend
- [ ] PostgreSQL persistence (Spring Data JPA)
- [ ] Angular frontend with charts
- [ ] Docker Compose and CI (GitHub Actions)
