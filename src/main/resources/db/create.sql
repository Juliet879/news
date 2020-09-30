CREATE DATABASE my_news;
\c my_news;


CREATE TABLE IF NOT EXISTS departments (
id SERIAL PRIMARY KEY,
  name VARCHAR,
  description VARCHAR,
  employeesNumber INTEGER
);

CREATE TABLE IF NOT EXISTS users (
id SERIAL PRIMARY KEY,
  name VARCHAR,
  companyPosition VARCHAR,
  role VARCHAR,
  departmentId INTEGER
);

CREATE TABLE IF NOT EXISTS news (
id SERIAL PRIMARY KEY,
  title VARCHAR,
  content VARCHAR,
  departmentId INTEGER
);


CREATE TABLE IF NOT EXISTS departments_news(
id SERIAL PRIMARY KEY,
departmentId INTEGER,
newsId INTEGER
);

CREATE DATABASE my_news_test WITH TEMPLATE my_news;

