# CRUD Parte Diario

CRUD principal de `ParteDiario` con relación 1:N con `Encargado`.

## Arranque

```bash
docker compose up -d
mvn spring-boot:run
```

Si usas el Maven Wrapper de tu proyecto generado por Spring Initializr:

```bash
./mvnw spring-boot:run
```

Abrir: http://localhost:8080/partes

MariaDB usa el puerto 3307, base de datos `crud-partediario`, usuario `user` y contraseña `password`.
