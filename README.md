\#  Microservicio de Laboratorios (DSY2205)



Microservicio REST desarrollado en \*\*Spring Boot 3 + Java 21\*\*.  

Expone endpoints para consultar, crear, actualizar y eliminar laboratorios de computación de la institución (nombre, ubicación, capacidad, encargado).  

Persiste datos en \*\*Oracle Database (Autonomous / Wallet TNS)\*\*.



---



\##  Contexto académico



Proyecto realizado para la asignatura \*\*DSY2205 - Desarrollo de Microservicios\*\*.  

Objetivos evaluados por el docente:

1\. Uso correcto de repositorio Git / control de versiones.

2\. CRUD completo conectado a base de datos Oracle.

3\. Exposición de endpoints a través de un microservicio Spring Boot.

4\. Consultas a tablas reales de la base de datos institucional.



Este repositorio cubre esos 4 puntos.



---



\##  Tecnologías principales



\- \*\*Java 21\*\*

\- \*\*Spring Boot 3.5.7\*\*

\- Spring Web (Controladores REST)

\- Spring Data JPA

\- HikariCP (pool de conexiones)

\- Oracle JDBC (ojdbc11)

\- Maven



---



\##  Configuración de la base de datos



Archivo: `src/main/resources/application.properties`



```properties

\# Puerto del microservicio

server.port=8081



\# Conexión Oracle Autonomous usando wallet TNS

spring.datasource.url=jdbc:oracle:thin:@bddsy2205\_high?TNS\_ADMIN=C:/microservicios/Wallet\_BDDSY2205

spring.datasource.username=ADMIN

spring.datasource.password=Florencia.2014

spring.datasource.driver-class-name=oracle.jdbc.OracleDriver



\# Pool de conexiones

spring.datasource.hikari.maximum-pool-size=5

spring.datasource.hikari.minimum-idle=1

spring.datasource.hikari.idle-timeout=30000

spring.datasource.hikari.pool-name=HikariPool-Laboratorios



\# JPA / Hibernate

spring.jpa.hibernate.ddl-auto=none

spring.jpa.show-sql=true

spring.jpa.properties.hibernate.format\_sql=true

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect



