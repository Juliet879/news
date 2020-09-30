CREATE DATABASE my_news;
\c my_news;


CREATE TABLE IF NOT EXISTS departments (
id SERIAL PRIMARY KEY,
  departmentName VARCHAR,
  departmentDescription VARCHAR,
  departmentEmployeesNumber INTEGER
);

CREATE TABLE IF NOT EXISTS users (
id SERIAL PRIMARY KEY,
  userName VARCHAR,
  userCompanyPosition VARCHAR,
  userCompanyRole VARCHAR,
  departmentId INTEGER
);

CREATE TABLE IF NOT EXISTS news (
id SERIAL PRIMARY KEY,
  newsTitle VARCHAR,
  newsContent VARCHAR,
  departmentId INTEGER
);

CREATE TABLE IF NOT EXISTS departments_users (
id SERIAL PRIMARY KEY,
departmentId INTEGER,
userId INTEGER
);

CREATE TABLE IF NOT EXISTS departments_news(
id SERIAL PRIMARY KEY,
departmentId INTEGER,
newsId INTEGER
);

CREATE DATABASE my_news_test WITH TEMPLATE my_news;

--use lines below for h2 database on local machine

--SET MODE PostgreSQL;
--
--CREATE TABLE IF NOT EXISTS departments (
--id int PRIMARY KEY auto_increment,
--  departmentName VARCHAR,
--  departmentDescription VARCHAR,
--  departmentEmployeesNumber INTEGER
--);
--
--CREATE TABLE IF NOT EXISTS users (
--id int PRIMARY KEY auto_increment,
--  userName VARCHAR,
--  userCompanyPosition VARCHAR,
--  userCompanyRole VARCHAR,
--  departmentId INTEGER
--);
--
--CREATE TABLE IF NOT EXISTS news (
--id int PRIMARY KEY auto_increment,
--  newsTitle VARCHAR,
--  newsContent VARCHAR,
--  departmentId INTEGER
--);
--
--CREATE TABLE IF NOT EXISTS departments_users (
--id int PRIMARY KEY auto_increment,
--departmentId INTEGER,
--userId INTEGER
--);
--
--CREATE TABLE IF NOT EXISTS departments_news(
--id int PRIMARY KEY auto_increment,
--departmentId INTEGER,
--newsId INTEGER
--);