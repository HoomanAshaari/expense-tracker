# 💰 Expense Tracker System 💰

## Overview

The Expense Tracker System is a backend service designed to help users
to manage their expenses efficiently.

You can register, login and track your daily expenses, categorize them,
and generate reports to get insights into your spending habits.

## 🧩 Features

- **Expense Management**
    - Manage (add, retrieve, edit, and delete) your expenses
    - Categorize expenses (e.g. Bills, Food, Transport, etc.)
    - View expenses by filters


- **Category management**
    - Manage predefined and custom expense categories


- **User Authentication**
    - Secure user login functionality


- **Report Generation**
    - Generate and view monthly expense reports


- **Financial goal setting**
    - Add your income
    - Set personalized financial goals
    - Add budget for your expenses

## 🚀 Technology Stack

- **Backend:**
    - Java 21
    - Spring Boot 3
    - Maven
    - Testcontainers


- **Database:**
    - MySQL

## 🔧 Installation

1) Go to [/setup-scripts](setup-scripts)
2) Use `./start-service`  and `./stop-service` to start and stop services

## 📔 How to use?

When you run the program, you can access a swagger endpoint at this url in
your localhost: http://localhost:8080/api/swagger-ui. Details about each
endpoint are provided in the OpenAPI documentation for you, but I'm going
to list main steps that you should take to be able to use the app.

1) First, you should create an account using
   [sign-up API](http://localhost:8080/expense-tracker/api/v1/users/sign-up).

2) Now that you have a user signed-up, you can log in using
   [login API](http://localhost:8080/expense-tracker/api/v1/users/login),
   which will provide you a JWT token that you should keep and send in
   **Authorization Header** of your later requests.

   (You can use swagger's Authorize feature, which will make the authentication
   process easier for you)

3) It's time to add some categories using
   [Category APIs](http://localhost:8080/expense-tracker/api/v1/categories).

4) Then you can add your expenses to each category using
   [Expense APIs](http://localhost:8080/expense-tracker/api/v1/expenses).

## Technical Decisions

- For now, I only used docker for running MySQL,
  one reason for that is because there is a challenge with making the current
  project dockerized (docker inside docker), because of the use of testcontainers.
  Later we may address it.
- I have used **Spring boot 3 and Java 21**, which are the LTS versions of them at this point.
- **SLF4J** has been used for logging events (for now we're only using console appender), also
  there is a custom logger aspect, logging input/outputs of controllers (except for users which are carrying sensitive
  data)
- **Spring security with a combination of JWT** has been used, which provides a
  stateless secure mechanism for authentication and authorization. (although
  authorization is not added yet.)
- **Spring Data JPA** has been used because of its great features for the accessing database.
- **MySQL database** has been chosen since it's a great performing open source SQL solution.


- I've tried to mostly implement main services using **TDD** approach:
    - Some **integration-tests** have been added for authentication mechanism testing.
    - Some **unit tests** have been added for main resources APIs and services.

## TODOs:

- Add support for timezones

- Currently, we are using console logger appender, add a proper
  log exporting/collecting mechanism

- Users and security services are getting tested under integration-tests,
  you can add some unit-tests for user and security related services

- Add authorization to the existing security mechanism (authentication has been already added)

## Licence

This Work is licensed under [GNU General Public License Version 3](LICENSE).