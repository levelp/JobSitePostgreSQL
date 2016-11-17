JobSite с использованием PostgreSQL
===================================

[![Build Status](https://travis-ci.org/levelp/JobSitePostgreSQL.svg?branch=master)](https://travis-ci.org/levelp/JobSitePostgreSQL)
[![Coverage Status](https://coveralls.io/repos/github/levelp/JobSitePostgreSQL/badge.svg?branch=master)](https://coveralls.io/github/levelp/JobSitePostgreSQL?branch=master)

В проекте используется Spring JPA (JPA = Java Persistence API)
который вызывает библиотеку
Hibernate для генерации и выполнения SQL-запросов
которая вызывает драйвер PostgreSQL для выполнения
запросов и специфичных "кусков" SQL запросов
Драйвер подключается к базе данных через JDBC-подключение.


Подключение к БД 
-----------------
* Database: postgres
* Port: 5432
* User: postgres
* Password: 123


Основные операторы SQL (Structured Query Language)
--------------------------------------------------
* SELECT - выбор записей из таблицы
* INSERT - добавление новых записей
* UPDATE - обновление (изменение) записей в таблице
* DELETE - удаление записей из таблицы

Удаление таблицы пользователей целиком:
```sql
DROP TABLE public.users;
```

