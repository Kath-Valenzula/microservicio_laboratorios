
# Microservicio de Laboratorios (DSY2205)

Este microservicio implementa el CRUD de laboratorios, persistiendo los datos en Oracle Autonomous Database. Desarrollado con Spring Boot 3.5.8 y Java 21, sigue un arquetipo propio para backend, estructurando el código en capas (controladores, servicios, repositorios, entidades y DTOs) para facilitar la mantenibilidad y la integración.

El trabajo colaborativo se gestiona mediante GIT, con ramas de desarrollo, revisiones por pull request y control de versiones. El repositorio mantiene la rama principal estable y documenta los cambios con mensajes claros y convenciones estándar.

La integración con el frontend Angular se realiza a través de APIs REST, cumpliendo con los requisitos de interoperabilidad y seguridad definidos para la asignatura.

## Tecnologías principales
- Java 21
- Spring Boot 3.5.8
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
```

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

## Documentacion de trabajo
- Arquetipo BackEnd: `docs/arquetipo-backend.md`
- Estrategia Git (Trunk Based Development): `docs/git-strategy.md`
