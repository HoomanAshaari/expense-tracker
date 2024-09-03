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

## TODOs:

- Add support for timezones

- Currently, we are using console logger appender, add a proper
  log exporting/collecting mechanism

- Users and security services are getting tested under integration-tests,
  you can add some unit-tests for user and security related services

## Licence

This Work is licensed under [GNU General Public License Version 3](LICENSE).