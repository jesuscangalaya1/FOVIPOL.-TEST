
# Sistema para administrar clientes.

Primero, debes ejecutar los scripts de la base de datos MySQL.

Luego, clona el repositorio y ejecuta el proyecto en tu IDE de preferencia, pero no olvides configurar las credenciales de la base de datos en el archivo `application.properties` ubicado en `src/main/resources/application.properties`.

El archivo `application.properties` debe contener la siguiente configuración:

```properties
spring.datasource.username=you_username
spring.datasource.password=you_password
```

## Tecnologias

- **Java Development Kit (JDK) 17:** Asegúrese de tener instalado JDK 17 en su sistema. Puede descargarlo desde [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) o [OpenJDK](https://adoptopenjdk.net/).

- **Spring Boot: 2.7.15**.

- **Base de datos MYSQL**

- **Swagger: http://localhost:8080/swagger-ui/index.html**


## Extras

- Uso de DTOs.
- @RestControllerAdvice para manejar excepciones globales en mi proyecto.
- Uso de Constantes para Mejor Legibilidad.
- Utilización de Genéricos para Flexibilidad.
- Campos validados.
- Documentación con Swagger.










