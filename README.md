# Proyecto Protectora Animales
El proyecto tiene la siguiente estructura:

![Diagrama de mi proyecto](imagenes/esquema.drawio.png)

# Guía de uso
1``mvn clean install`` para instalar las dependencias del proyecto.
2``docker-compose up --build`` para levantar Rabbitmq , Zipkin y Postgres.
4. Arrancar desde el IDE el servicio Config Server.
5. Arrancar desde el IDE el servicio Eureka Server.
6. Arrancar desde el IDE el servicio Gateway Server.
7. Arrancar desde el IDE los servicios Voluntarios, Adoptantes, Animales y Adopciones.


# Documentación
- Config server: http://localhost:8888/<nombre_servicio>/default
- Eureka server: http://localhost:8761/
- Gateway: http://localhost:9000/
- Voluntarios: http://localhost:8080/swagger-ui.html
- Animales: http://localhost:8082/swagger-ui.html
- Adoptantes: http://localhost:8081/swagger-ui.html
- Adopciones:http://localhost:8083/swagger-ui.html
- Zipkin: http://localhost:9411/


# Autor
Miguel Díez García