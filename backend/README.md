BEFORE STARTING
=
+ Java 11 installed.
+ PostgreSQL

#### To Genrate the spring app.
> https://start.spring.io/



Spring Config
=
```properties
server.port=9090
spring.datasource.platform=postgresql
spring.datasource.url=jdbc:postgresql://localhost:5432/documentstore
spring.datasource.username=injest
spring.datasource.password=injest123
spring.jpa.show-sql=true
spring.jpa.generate-ddl=true
spring.jpa.hibernate.ddl-auto=create
spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
spring.datasource.initialization-mode=always
```

ENDPOINTS
=


PostgreSQL
=

> https://www.codementor.io/engineerapart/getting-started-with-postgresql-on-mac-osx-are8jcopb

PostgreSQL frequently used commands:
```sql
SELECT * FROM books;
\dt

```





