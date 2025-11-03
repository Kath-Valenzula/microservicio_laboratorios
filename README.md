# Microservicio de Laboratorios (DSY2205)

Microservicio REST con **Spring Boot 3.5.7** y **Java 21**. CRUD de laboratorios (nombre, ubicación, capacidad, encargado) persistido en **Oracle Autonomous Database** mediante **Wallet TNS**.

## Contexto
Asignatura **DSY2205 - Desarrollo de Microservicios**. Cubre: uso de Git, CRUD completo con Oracle, controladores REST y consultas a tablas reales.

## Tecnologías
- Java 21
- Spring Boot 3.5.7
- Spring Web
- Spring Data JPA
- HikariCP
- Oracle JDBC (ojdbc11)
- Maven

## Configuración
Archivo: `src/main/resources/application.properties`
```properties
server.port=8081

spring.datasource.url=jdbc:oracle:thin:@bddsy2205_high?TNS_ADMIN=C:/microservicios/Wallet_BDDSY2205
spring.datasource.username=ADMIN
spring.datasource.password=********
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

spring.datasource.hikari.pool-name=HikariPool-Laboratorios
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.idle-timeout=30000

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
````

## Build y ejecución

```powershell
mvn clean package -DskipTests
java "-Doracle.net.tns_admin=C:\microservicios\Wallet_BDDSY2205" -jar target\microservicio-laboratorios-0.0.1-SNAPSHOT.jar
```

## Endpoints

Base URL: `http://localhost:8081/api/laboratorios`

* `GET /api/laboratorios`
* `GET /api/laboratorios/{id}`
* `POST /api/laboratorios`
* `PUT /api/laboratorios/{id}`
* `DELETE /api/laboratorios/{id}`

## Pruebas rápidas (PowerShell cURL)

```powershell
curl http://localhost:8081/api/laboratorios
curl http://localhost:8081/api/laboratorios/1
curl -X POST "http://localhost:8081/api/laboratorios" ^
  -H "Content-Type: application/json" ^
  -d "{`"nombre`":`"Lab Redes`",`"ubicacion`":`"Edificio A`",`"capacidad`":30,`"encargado`":`"Juan`"}"
curl -X PUT "http://localhost:8081/api/laboratorios/1" ^
  -H "Content-Type: application/json" ^
  -d "{`"nombre`":`"Lab Redes 2`",`"ubicacion`":`"Edificio B`",`"capacidad`":28,`"encargado`":`"Ana`"}"
curl -X DELETE "http://localhost:8081/api/laboratorios/1"
```
