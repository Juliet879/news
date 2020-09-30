# NEWS-PORTAL API

## Author
Juliet Kemunto Gisemba

### Description
This is an Organizational Rest API that queries and retrieves scoped news and information within an organization.

## Features
A user visiting the news portal Api site will be able to:

1. Add New Departments & and also view local departments present.
2. Add new User/s' to andy department desired.
3. Add news in a particular department and view the news under the directed department.

## Prerequisites & Application Setup
- Use of version control. ie. Git
- Java SDK & Environment.
- Java IntelliJIDEA Community.
- Gradle.
- PostgreSQL

### Clone Repository

- Depending on your preffereable method of acquiring the Application to your local machine, you might as well try:
1. Forking the repository to your repositories.
2. Cloning the forked repository or cloning directly from my repository.
3. Finally run the project on your IntelliJIDEA `$ gradle build`, `$ gradle run` in your projects terminal.

## Expected Behaviour. (USERS' GUIDE)
### View Main Landing Page.
* Method: GET
* Route: /

{
    message: "Welcome to NEWS API"
}

- The returned Json is as seen above.

### View Departments
* Method: GET
* Route: `/departments`

### View Departments by ID
* Method: GET
* Route: `/departments/:id`

### View Departments via Sorted News
* Method: GET
* Route: `/departments/:id/sortedNews`

- Sort news in accordance to time passed since it was posted.

### View Users by ID
* Method: GET
* Route: `/users/:id`

## Technologies Used
- Java
- Java Spark*
- PostgreSQL
- Postman

## Bugs
~ At the moment there are no known bugs.

## Contact Information
~ For any inquiries or future git collaborative projects, reach out to:

Email: <a href="julietgisemba000@gmail.com">julietgisemba000@gmail.com</a>

## LICENSE
Copyright 2020 Juliet Gisemba Kemunto.
