# Bank API - Current Account System

A RESTful banking API built with Spring Boot that supports
account creation, deposits, withdrawals, and transfers.

## 🔧 Architecture
Controller → Service → Repository → Entity

## 📃 API Documentation

This project provides interactive API documentation using Swagger.

After starting the application, you can access the documentation at:

http://localhost:8080/swagger-ui.html

The OpenAPI specification is available at:

http://localhost:8080/v3/api-docs

## 💻 Run the application

To run the application, first open the folder which contains this repository and then run these 3 commands in the following order:
<br>
<br>
  <b>1.</b> cd ./braguia
  <br>
  <b>2.</b> docker-compose up -d
  <br>
  <b>3.</b> .\gradlew bootrun

Although the main objective of this project is the use of the API, a minimalistic website is available at http://localhost:8080

## 🎯 System objective

Allow clients to:

* Create an account
* Check their balance
* Make deposits
* Make withdrawals
* Transfer funds between accounts
* View their statement


## 🧱 Basic system structure
👤 Clients<br>
 &nbsp; |---------------> 💳 Accounts<br> 
 &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; |--------------> 💸 Transactions

## 🧠 Bank rules

* ❌ You cannot withdraw if your balance is insufficient.
* ❌ You cannot transfer a negative amount.
* ❌ CPF numbers cannot be duplicated.
* ✔️ Every movement must generate a transaction.
* ✔️ Transfers must debit one account and credit another (atomic transaction).

## 🛠 Technologies used

* Java
* Spring Boot
* Spring Data JPA
* Hibernate
* Database: MySQL
* Testing with JUnit
* Docker

## 🚀 @TODO in the future

* Authentication with JWT
* Access control (clients only see their own accounts)
* Pagination in statements
* Global exception handling
* Audit logs
