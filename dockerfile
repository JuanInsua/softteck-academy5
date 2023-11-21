# Etapa de compilación
FROM maven:3.8.3-openjdk-17 AS builder

# Se copian los archivos del directorio "scr" al contenedor container
COPY src /home/app/src

# Se copia el archivo pom.xml al contenedor
COPY pom.xml /home/app

# Se ejecuta el comando "mvn", ubicando el archivo pom.xml
RUN mvn -f /home/app/pom.xml clean package

# Expone el puerto en el que la aplicación se ejecutará
EXPOSE 8080

# Comando para ejecutar la aplicación
CMD ["java", "-jar", "/home/app/target/actividad5-0.0.1-SNAPSHOT.jar"]