# Sistema RFID - API REST de categorías de activos

Proyecto desarrollado para la evidencia **GA7-220501096-AA3-EV01: Codificación de módulos del software stand-alone, web y móvil**, del programa Tecnólogo en Análisis y Desarrollo de Software - ADSO del Servicio Nacional de Aprendizaje - SENA.

## Información del aprendiz

- **Aprendiz:** Cristian Camilo Betancur Alzate
- **Programa:** Tecnólogo en Análisis y Desarrollo de Software - ADSO
- **Proyecto formativo:** Sistema RFID de Gestión de Activos
- **Evidencia:** GA7-220501096-AA3-EV01

## Descripción

Esta aplicación implementa una API REST para administrar las categorías de activos del Sistema RFID de Gestión de Activos.

El módulo permite registrar, consultar, actualizar y eliminar categorías mediante solicitudes HTTP. La persistencia se realiza en MySQL utilizando Spring Data JPA e Hibernate.

Esta evidencia representa la evolución del módulo `CategoriaActivo` desarrollado previamente. En esta versión se aplica el framework Spring Boot y una arquitectura organizada por capas.

## Objetivo

Codificar un módulo del proyecto formativo mediante un framework Java, manteniendo coherencia con los artefactos elaborados previamente y aplicando:

- Estándares de codificación.
- Comentarios y documentación del código.
- Persistencia de datos.
- Arquitectura por capas.
- Pruebas funcionales.
- Control de versiones con Git.
- Repositorio remoto en GitHub.

## Tecnologías utilizadas

- Java 21.
- Apache Maven.
- Spring Boot 3.2.12.
- Spring Web y Spring MVC.
- Spring Data JPA.
- Hibernate ORM.
- Jakarta Persistence.
- Jakarta Validation.
- MySQL.
- MySQL Connector/J.
- Apache Tomcat integrado en Spring Boot.
- JUnit 5.
- Postman.
- Git y GitHub.
- Apache NetBeans.

## Arquitectura

La aplicación utiliza una arquitectura por capas:

```text
Cliente HTTP o Postman
        |
        | Solicitud HTTP y JSON
        v
CategoriaActivoController
        |
        v
CategoriaActivoService
        |
        v
CategoriaActivoRepository
        |
        v
Spring Data JPA / Hibernate
        |
        v
MySQL
```

### Modelo

`CategoriaActivo.java` representa la entidad persistente y contiene:

- `idCategoria`
- `nombre`
- `descripcion`
- `activo`

También contiene las validaciones aplicadas a los datos recibidos por la API.

### Repositorio

`CategoriaActivoRepository.java` extiende `JpaRepository` y proporciona las operaciones de persistencia:

- Consultar todos los registros.
- Consultar por identificador.
- Guardar registros.
- Actualizar registros.
- Comprobar existencia.
- Eliminar registros.

Spring Data JPA genera automáticamente la implementación del repositorio.

### Servicio

`CategoriaActivoService.java` contiene la lógica del módulo:

- Consulta de categorías.
- Registro de categorías.
- Actualización de categorías.
- Eliminación de categorías.
- Normalización de textos.
- Administración de transacciones.

### Controlador

`CategoriaActivoController.java` expone los endpoints REST y devuelve respuestas JSON con estados HTTP apropiados.

### Manejo de excepciones

`ManejadorGlobalExcepciones.java` centraliza los errores de la API y evita exponer trazas técnicas al cliente.

## Estructura del proyecto

```text
rfid-categoria-activo-spring/
|-- pom.xml
|-- README.md
|-- .gitignore
|-- ENLACE_REPOSITORIO.txt
|-- database/
|   `-- configuracion_rfid.sql
`-- src/
    |-- main/
    |   |-- java/
    |   |   `-- co/edu/sena/rfid/
    |   |       |-- RfidCategoriaActivoSpringApplication.java
    |   |       |-- controlador/
    |   |       |   `-- CategoriaActivoController.java
    |   |       |-- excepcion/
    |   |       |   `-- ManejadorGlobalExcepciones.java
    |   |       |-- modelo/
    |   |       |   `-- CategoriaActivo.java
    |   |       |-- repositorio/
    |   |       |   `-- CategoriaActivoRepository.java
    |   |       `-- servicio/
    |   |           `-- CategoriaActivoService.java
    |   `-- resources/
    |       `-- application.properties
    `-- test/
        `-- java/
            `-- co/edu/sena/rfid/
                `-- RfidCategoriaActivoSpringApplicationTests.java
```

## Base de datos

- **Motor:** MySQL.
- **Base de datos:** `rfid_gestion_activos`.
- **Tabla:** `categoriaactivo`.

### Estructura utilizada

| Columna | Tipo | Restricciones | Descripción |
|---|---|---|---|
| `id_categoria` | `INT` | PK, AI, NN | Identificador de la categoría |
| `nombre` | `VARCHAR(100)` | NN | Nombre de la categoría |
| `descripcion` | `VARCHAR(255)` | NN | Descripción de la categoría |
| `activo` | `TINYINT(1)` | NN, DEFAULT 1 | Estado activo o inactivo |

El script de configuración se incluye en:

```text
database/configuracion_rfid.sql
```

## Configuración de credenciales

La aplicación no almacena credenciales reales en el código fuente.

`application.properties` utiliza estas variables de entorno:

```text
RFID_DB_USER
RFID_DB_PASSWORD
```

### Ejemplo en Windows CMD

Reemplace los valores por credenciales locales válidas:

```bat
setx RFID_DB_USER "rfid_app"
setx RFID_DB_PASSWORD "CONTRASENA_LOCAL"
```

Después de crear o modificar las variables, cierre y vuelva a abrir NetBeans o la consola utilizada para ejecutar el proyecto.

No publique contraseñas reales en GitHub, archivos comprimidos, capturas o documentación.

## Configuración principal

El archivo `application.properties` configura:

```properties
spring.application.name=RFIDCategoriaActivoSpring
spring.datasource.url=jdbc:mysql://localhost:3306/rfid_gestion_activos?useSSL=false&serverTimezone=America/Bogota&allowPublicKeyRetrieval=true
spring.datasource.username=${RFID_DB_USER}
spring.datasource.password=${RFID_DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=none
spring.jpa.open-in-view=false
spring.jpa.show-sql=false
server.port=8081
server.error.include-stacktrace=never
server.error.include-exception=false
```

La estructura de la base de datos se administra mediante el script SQL. Hibernate no crea ni modifica las tablas.

## Endpoints REST

Ruta base:

```text
http://localhost:8081/api/categorias
```

| Método | Endpoint | Función | Respuesta esperada |
|---|---|---|---|
| `GET` | `/api/categorias` | Listar categorías | `200 OK` |
| `GET` | `/api/categorias/{id}` | Consultar por ID | `200 OK` o `404 Not Found` |
| `POST` | `/api/categorias` | Registrar categoría | `201 Created` |
| `PUT` | `/api/categorias/{id}` | Actualizar categoría | `200 OK` o `404 Not Found` |
| `DELETE` | `/api/categorias/{id}` | Eliminar categoría | `204 No Content` o `404 Not Found` |

## Ejemplos de solicitudes

### Registrar una categoría

```http
POST /api/categorias
Content-Type: application/json
```

```json
{
  "nombre": "Equipos de red",
  "descripcion": "Switches, routers y puntos de acceso",
  "activo": true
}
```

Respuesta esperada:

```text
201 Created
```

### Consultar una categoría

```http
GET /api/categorias/8
```

Respuesta esperada si existe:

```text
200 OK
```

### Actualizar una categoría

```http
PUT /api/categorias/8
Content-Type: application/json
```

```json
{
  "nombre": "Equipos de conectividad",
  "descripcion": "Switches, routers y puntos de acceso institucionales",
  "activo": false
}
```

Respuesta esperada:

```text
200 OK
```

### Eliminar una categoría

```http
DELETE /api/categorias/8
```

Respuesta esperada:

```text
204 No Content
```

## Validaciones

La API valida:

- Nombre obligatorio.
- Nombre con máximo 100 caracteres.
- Descripción obligatoria.
- Descripción con máximo 255 caracteres.

Ejemplo inválido:

```json
{
  "nombre": "",
  "descripcion": "",
  "activo": true
}
```

Respuesta esperada:

```text
400 Bad Request
```

Ejemplo de respuesta organizada:

```json
{
  "fechaHora": "2026-10-08T18:10:55",
  "estado": 400,
  "error": "Bad Request",
  "mensaje": "Los datos enviados no son válidos.",
  "errores": {
    "nombre": "El nombre es obligatorio.",
    "descripcion": "La descripción es obligatoria."
  }
}
```

## Requisitos para ejecutar

- JDK 21.
- Maven 3.6.3 o superior, o Maven integrado en NetBeans.
- MySQL en ejecución.
- Base `rfid_gestion_activos` creada.
- Tabla `categoriaactivo` creada.
- Variables `RFID_DB_USER` y `RFID_DB_PASSWORD` configuradas.
- Puerto `8081` disponible.

No es necesario instalar Tomcat por separado porque Spring Boot utiliza un servidor integrado.

## Compilación

Desde la raíz del proyecto:

```bat
mvn clean package
```

En NetBeans también puede utilizarse:

```text
Clic derecho sobre el proyecto
→ Clean and Build
```

Resultado esperado:

```text
Tests run: 1
Failures: 0
Errors: 0
BUILD SUCCESS
```

El JAR ejecutable se genera en:

```text
target/RFIDCategoriaActivoSpring.jar
```

## Ejecución

### Desde NetBeans

```text
Clic derecho sobre el proyecto
→ Run
```

### Desde consola

```bat
java -jar target/RFIDCategoriaActivoSpring.jar
```

La API debe iniciar en:

```text
http://localhost:8081
```

## Pruebas realizadas

Se comprobaron mediante ejecución las siguientes operaciones:

| Prueba | Resultado |
|---|---|
| Inicio del contexto de Spring Boot | Correcto |
| Conexión mediante HikariCP y MySQL | Correcto |
| Listado general | `200 OK` |
| Registro válido | `201 Created` |
| Consulta por ID existente | `200 OK` |
| Actualización existente | `200 OK` |
| Eliminación existente | `204 No Content` |
| Consulta posterior a eliminación | `404 Not Found` |
| Registro con campos vacíos | `400 Bad Request` |
| Manejo global de errores de validación | Correcto |
| Compilación y prueba automática | `BUILD SUCCESS` |
| Generación del JAR ejecutable | Correcto |

Las solicitudes HTTP se probaron con Postman.

## Prueba automática

La clase:

```text
RfidCategoriaActivoSpringApplicationTests.java
```

utiliza `@SpringBootTest` para comprobar que el contexto completo de Spring pueda iniciarse correctamente.

La prueba requiere que MySQL esté disponible y que las variables de entorno hayan sido configuradas.

## Seguridad y buenas prácticas

- Credenciales externas al código fuente.
- Inyección de dependencias por constructor.
- Separación entre Controller, Service, Repository y Model.
- Validaciones declarativas con Jakarta Validation.
- Respuestas HTTP apropiadas.
- Manejo global de excepciones.
- Trazas internas ocultas al cliente.
- Registro interno de errores inesperados.
- Transacciones en la capa de servicio.
- Normalización de datos textuales.
- Código comentado y organizado por paquetes.
- Dependencias administradas mediante Maven.

## Control de versiones

El proyecto utiliza Git para registrar los cambios y GitHub como repositorio remoto.

El enlace definitivo se incluye en:

```text
ENLACE_REPOSITORIO.txt
```

El repositorio no debe incluir:

```text
target/
contraseñas
archivos temporales
configuraciones privadas del IDE
```

## Alcance

La evidencia implementa únicamente el módulo de categorías de activos como API REST.

No incluye todavía:

- Autenticación y autorización.
- Gestión de usuarios y roles.
- Interfaz gráfica propia.
- Documentación Swagger/OpenAPI.
- Paginación y búsqueda avanzada.
- Integración con lectores RFID.
- Integración con otros módulos del proyecto.

Estos elementos corresponden a posibles evoluciones posteriores del Sistema RFID.

## Trazabilidad con el proyecto formativo

El módulo conserva coherencia con los artefactos previos del Sistema RFID:

- Entidad `CategoriaActivo`.
- Tabla `categoriaactivo`.
- Operaciones CRUD previstas para administrar categorías.
- Base de datos `rfid_gestion_activos`.
- Arquitectura organizada y reutilizable.
- Evolución desde JDBC y Servlets hacia Spring Boot y Spring Data JPA.

## Autor

**Cristian Camilo Betancur Alzate**  
Tecnólogo en Análisis y Desarrollo de Software - ADSO  
Servicio Nacional de Aprendizaje - SENA
