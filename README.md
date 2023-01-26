# peliculas-core-service
Servicio core que contiene la mayor parte de funcionalidad sobre webSite de peliculas

-Debes dirigirte hacia "application.properties" para configurar la informacion para la conexion a base de datos.
Tomar en cuenta que solamente es necesario crear la base de datos previamente.

-Como se tiene la declaracion "spring.jpa.hibernate.ddl-auto=update" en "application.properties", la tablas se generaran y mapearan automaticamente.

-En "application.properties" en la declaracion "server.port" podras configurar el puesto de despliegue del servicio.

-En "application.properties" en la declaracion "endpoint.validate-token" se puede configurar el path o url en 
donde se encuentra el metodo para validar el token del servicio peliculas-security-service.
