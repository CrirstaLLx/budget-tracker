# Budget Tracker

A personal finance tracker built in Java: manage accounts, income and expense transactions, and monthly budgets per category.

> **Status:** work in progress. The core domain logic and unit tests are done; the REST API, database and frontend are planned.

## Tech stack

- Java 21
- Maven
- JUnit 5

**Planned:** Spring Boot (REST API), PostgreSQL, Angular

## What is implemented

- Domain model: `Account`, `Transaction`, `Budget`, `ExpenseCategory`, `TransactionType`
- `FinanceManager` service:
  - adding accounts, transactions and budgets
  - validation: a transaction can only be added to a known account
  - account balance calculation (initial balance + income - expenses)
  - monthly expenses grouped by category
- `BigDecimal` for all monetary values to avoid floating-point rounding errors
- Unit tests for balance calculation and monthly category totals (including month filtering)

## Project structure

```
src/main/java/com/crirstallx/budgettracker
├── model      # data classes and enums
├── service    # business logic (FinanceManager)
└── Main.java  # console demo
src/test/java  # JUnit tests
```

## How to run

Requirements: JDK 21 and Maven.

```bash
# run the tests
mvn test

# run the console demo
mvn compile
java -cp target/classes com.crirstallx.budgettracker.Main
```

The demo creates an account, adds several transactions and prints the balance and the October 2026 expenses by category.

## Roadmap

- [ ] Budget checks (budget status per category and month, replace duplicate budgets)
- [ ] Monthly spending trend
- [ ] Spring Boot REST API with validation
- [ ] PostgreSQL persistence (Spring Data JPA)
- [ ] Angular frontend with charts
- [ ] Docker Compose and CI (GitHub Actions)
