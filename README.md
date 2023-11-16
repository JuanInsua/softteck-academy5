# To-Do App

## Descripción

Esta es una aplicación de lista de tareas que permite a los usuarios gestionar y organizar sus tareas diarias. La aplicación está construida utilizando Java con Spring Boot para el backend y Thymeleaf para el frontend.

## Requisitos Previos

- JDK 8 o superior
- MySQL
- Maven

## Configuración del Entorno de Desarrollo

1. **Clonar el Repositorio**

##### bash
git clone https://github.com/tu-usuario/todo-app.git
cd todo-app
Configurar la Base de Datos

Crea una base de datos MySQL remota.
Actualiza las configuraciones de la base de datos en el archivo application.properties dentro del proyecto Spring Boot.
Configurar Variables de Entorno

Abre el proyecto en tu IDE y configura las variables de entorno necesarias.
Puedes configurar la conexión a la base de datos, el puerto del servidor, etc., según sea necesario.
Ejecutar la Aplicación

Inicia la aplicación desde tu IDE o usando Maven:

##### bash
mvn spring-boot:run
La aplicación estará disponible en http://localhost:puerto.

## Estructura del Proyecto
todo-app/
|-- src/
|   |-- main/
|       |-- java/
|       |   |-- com/
|       |       |-- tu/
|       |           |-- app/
|       |               |-- controllers/
|       |               |-- models/
|       |               |-- repositories/
|       |               |-- security/
|       |               |-- services/
|       |               |-- TodoAppApplication.java
|       |-- resources/
|           |-- static/
|           |-- templates/
|           |-- application.properties
|-- pom.xml
|-- otros-archivos
## Uso
Accede a la aplicación desde tu navegador utilizando http://localhost:puerto.
Inicia sesión con tu usuario y contraseña.
Gestiona tus tareas utilizando la interfaz de usuario.
## Contribuciones
Si deseas contribuir, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama: git checkout -b feature/nueva-funcionalidad
3. Haz tus cambios y realiza un commit: git commit -m 'Agrega nueva funcionalidad'
4. Empuja la rama a tu fork: git push origin feature/nueva-funcionalidad
5. Crea un pull request en el repositorio original.