JobSite с использованием PostgreSQL
===================================

[![Build Status](https://travis-ci.org/levelp/JobSitePostgreSQL.svg?branch=master)](https://travis-ci.org/levelp/JobSitePostgreSQL)
[![Coverage Status](https://coveralls.io/repos/github/levelp/JobSitePostgreSQL/badge.svg?branch=master)](https://coveralls.io/github/levelp/JobSitePostgreSQL?branch=master)

Мы используем spring JPA - java persistence API
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


SQL
---

SELECT - выбор записей из таблицы
```


Удаление таблицы
``` sql
DROP TABLE public.users;
```

