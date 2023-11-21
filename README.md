To-Do App :coffee:

## Descripción  :page_with_curl:

Esta es una aplicación de lista de tareas que permite a los usuarios gestionar y organizar sus tareas diarias. La aplicación está construida utilizando [Java](https://docs.oracle.com/en/java/) con [Spring Boot](https://spring.io) para el backend y [Thymeleaf](https://www.thymeleaf.org/documentation.html) para el frontend.

## Requisitos Previos  :key:

- JDK 8 o superior
- MySQL
- Maven

## Configuración del Entorno de Desarrollo

1. **Clonar el Repositorio**

git clone https://github.com/JuanInsua/softteck-academy5 cd todo-app

2. **Configurar la Base de Datos**

Crea una base de datos MySQL remota.    
Actualiza las configuraciones de la base de datos en el archivo application.properties dentro del proyecto Spring Boot.

3. **Configurar Variables de Entorno**

Abre el proyecto en tu IDE y configura las variables de entorno necesarias.    
Puedes configurar la conexión a la base de datos, el puerto del servidor, etc., según sea necesario.    
Ejecutar la Aplicación

4. **Inicia la aplicación desde tu IDE o usando Maven:**

mvn spring-boot:run    
La aplicación estará disponible en http://localhost:puerto.  (próximamente)

## Uso  :eyes:
Accede a la aplicación desde tu navegador.    
Inicia sesión con tu usuario y contraseña.    
Gestiona tus tareas utilizando la interfaz de usuario.
## Contribuciones  :hammer:
Si deseas contribuir, sigue estos pasos:

1. Haz un fork del proyecto.
2. Crea una nueva rama: git checkout -b feature/nueva-funcionalidad
3. Haz tus cambios y realiza un commit: git commit -m 'Agrega nueva funcionalidad'
4. Empuja la rama a tu fork: git push origin feature/nueva-funcionalidad
5. Crea un pull request en el repositorio original.

## Contenerización en Docker :whale:

#### Construcción de la Imagen Docker

Para ejecutar la aplicación en un contenedor Docker, sigue estos pasos:

1.  Asegúrate de tener Docker instalado en tu máquina.

2.  Abre una terminal y navega al directorio raíz del proyecto.

3.  Ejecuta el siguiente comando para construir la imagen Docker:

    `docker build -t nombre-de-la-imagen .`

#### Ejecución del Contenedor Docker

Una vez que la imagen Docker está construida, puedes ejecutar la aplicación en un contenedor con el siguiente comando:

`docker run -p puerto-del-host:puerto-del-contenedor -d nombre-de-la-imagen`

La aplicación estará disponible en `http://localhost:puerto-del-host`.

## Despliegue en Kubernetes :six_pointed_star:

#### Configuración de Kubernetes

Antes de desplegar la aplicación en Kubernetes, asegúrate de tener un clúster de Kubernetes configurado y accesible.

1.  Actualiza los archivos de configuración de Kubernetes (por ejemplo, `deployment.yaml`, `service.yaml`) con la información específica de tu aplicación, como la imagen Docker y los puertos necesarios.

2.  Aplica la configuración en tu clúster de Kubernetes:

    `kubectl apply -f deployment.yaml
    kubectl apply -f service.yaml`


#### Acceso a la Aplicación en Kubernetes

Para acceder a la aplicación en Kubernetes, encuentra la dirección IP externa o el nombre del servicio asignado y abre tu navegador en `http://direccion-ip-o-nombre-del-servicio:puerto`.

### Contribuciones :hammer:

Si deseas contribuir a la aplicación, puedes seguir los pasos anteriores y considerar añadir funcionalidades específicas para el entorno de contenedores y Kubernetes. Asegúrate de proporcionar instrucciones claras para la construcción de imágenes y el despliegue en tu entorno de desarrollo local y en entornos Kubernetes. ¡Gracias por contribuir!


### Gracias !:hotsprings: