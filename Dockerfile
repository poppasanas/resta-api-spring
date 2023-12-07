FROM amazoncorretto:21.0.1
COPY ./out/artifacts/resta_api_spring_jar/resta-api-spring.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]