# How to restore db Dump

`dump.sql` is generated via `pg_dump`

So, to restore, first install postgresql

Then, use `psql -U db_user db_name < dump.sql` to restore it.


### DB Configuration for Backend

open `backend/src/main/resources`

copy `base-application.properties` to `application.properties`

enter the connection string, user and password of your postgresql db
after uncommenting below lines.
```
spring.datasource.url=jdbc:postgresql://
spring.datasource.username=user
spring.datasource.password=pass
```


### Frontend Configuration for connecting to Local Backend

in `frontend/package.json`

change the line 
```
  "proxy": "https://api.bounswe2019group9.tk",
```
with the desired url.

And then start the application with `npm start` after running `npm install`

(Make sure you have node 10)