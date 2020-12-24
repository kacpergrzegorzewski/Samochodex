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

## Features

- retrieving data from a database and displaying them on the website
- logging to the website excluding home page

## To do list

1. Add user registration
    - credentials stored in DB
    - hashed passwords with bcrypt
    - 3 types of user account (admin, worker, client) - each with different permissions

2. Improve web design
    - css/js (React framework)
    - change login site
    - add csrf token in authentication form via React

3. Use ORM
    - for big databases its better and convenient to use ORM
    - Hibernate framework
    - easier, baster, better way of defining database objects

4. Create own server to host the application
    - azure ???
    - if this won't be working we will use hosting server (Heroku)

## How to contribute

If you want to add any feature to the project please create a new branch from the `develop`.
When you complete implementing feature please create pull request back to the `develop` branch
and wait for the acceptation.
