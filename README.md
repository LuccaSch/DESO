# UTN FRSF DS TP Project build with Spring Boot and Docker Compose (MySQL and phpMyAdmin)

## Development

When starting the application `docker compose up` is called and the app will connect to the contained services.
[Docker](https://www.docker.com/get-started/) must be available on the current system.

During development it is recommended to use the profile `local`. In IntelliJ `-Dspring.profiles.active=local` can be
added in the VM options of the Run Configuration after enabling this property in "Modify options". Create your own
`application-local.properties` file to override settings for development.

After starting the application it is accessible under `localhost:8080`.

## Build

The application can be built using the following command:

```
./mvnw clean package
```

Start your application with the following command:

```
./mvnw spring-boot:run
```

You can use ```./mvnw``` or ```./mvn``` either.

If required, a Docker image can be created with the Spring Boot plugin. Add `SPRING_PROFILES_ACTIVE=production` as
environment variable when running the container.

```
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=isi.deso/tp-spring
```

## Interactive Documentation Generation Swagger UI

```
For interactive http transactions use [SwaggerUI](localhost:8080/swagger-ui/index.html#)
```

## App Ports

```
For phpMyAdmin, use port 8081 (localhost:8081/)
```

```
For App UI, use port 8080 (localhost:8080/)
```

## Further readings

* [Maven docs](https://maven.apache.org/guides/index.html)  
* [Spring Boot reference](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)  
* [Spring Data JPA reference](https://docs.spring.io/spring-data/jpa/reference/jpa.html)
