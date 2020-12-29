# Samochodex Web Application

![Java CI with Maven badge](https://github.com/kacpergrzegorzewski/Samochodex/workflows/Java%20CI%20with%20Maven/badge.svg)

## About

Samochodex is a web application for Database and Big Data course.
Developing by two University of Technology students.

## Frameworks

- Java with Maven
- Spring Boot
- Spring Security
- Thymeleaf
- JDBC with Oracle database
- JPA and Hibernate

## Features

- retrieving data from a database and displaying them on the website
- logging to the website excluding home page
- https connection and self-signed ssl certificate (very trusted)
- user credentials stored in a database on a very secure server and password stored are encrypted (not MD5)
- user registration

## To do list

1. Add user registration
    - add registration page to register new users and add their credentials to database
    - add password check (length)
    - add verification via email

2. Improve web design
    - html/css/js (React framework)
    - add csrf token in authentication form via React

3. Use ORM
    - for big databases its better and convenient to use ORM
    - Hibernate framework
    - easier, baster, better way of defining database objects

4. Create own server to host the application
    - azure ???
    - if this won't be working we will use hosting server (Heroku)
    - add trusted ssl certificate to the server... maybe

5. Encrypt personal data in application.properties

6. Proper site
    - first /index, in it:
        * register and successful register site
        * login
        * show current user logged in
        * logout if user is sign-in
        * buttons to other html files (poczty, salony, użytkownicy), secured with role based authorization
    - proper URL structure (things forbidden for normal clients, employees in separate URL paths)

7. This is how site should look like from client account
![Strona z konta klienta](https://user-images.githubusercontent.com/52577030/103287091-eb5ad580-49e1-11eb-84cc-c87aefc6074a.png)

## How to contribute

If you want to add any feature to the project please create a new branch from the `develop`.
When you complete implementing feature please create pull request back to the `develop` branch
and wait for the acceptation.
