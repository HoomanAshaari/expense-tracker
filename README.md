# 💰 Expense Tracker System 💰

## Overview

The Expense Tracker System is a backend service designed to help users
to manage their expenses efficiently.

You can register, login and track you daily expenses, categorize them,
and generate reports to get insights into your spending habits.

Also to this point, I've tried to mostly implement it using TDD approach.

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


- **Database:**
    - MySQL

## 🔧 Installation

1) Set up a MySQL server
2) Make sure scripts in [db directory](src/main/resources/db) get
   executed in your database environment (flyway dependency will
   be added soon to make this process easier)

## 📔 How to use?

When you run the program, you can access a swagger endpoint at this url in
your localhost: http://localhost:8080/api/swagger-ui. Details about each
endpoint are provided in the OpenAPI documentation for you, but I'm going
to list main steps that you should take to be able to use the app.

1) First you should create an account using
   [sign-up API](http://localhost:8080/expense-tracker/api/v1/users/sign-up).

2) Now that you have a user signed-up, you can log in using
   [login API](http://localhost:8080/expense-tracker/api/v1/users/login),
   which will provide you a JWT token that you should keep and send in
   **Authorization Header** of your later requests.

   (You can use swagger's Authorize feature, which will make authentication process easier for you)

3) It's time to add some categories utilizing
   [Category APIs](http://localhost:8080/expense-tracker/api/v1/categories).

4) Then you can add your expenses to each category using
   [Expense APIs](http://localhost:8080/expense-tracker/api/v1/expenses).

## TODOs:

- Add support for timezones

- Currently, we are using console logger appender, add a proper
  log exporting/collecting mechanism

- Users and security services are getting tested under integration-tests,
  you can add some unit-tests for user and security related services

- Add authorization to the existing security mechanism (authentication has been already added)

## Licence

This Work is licensed under [GNU General Public License Version 3](LICENSE).