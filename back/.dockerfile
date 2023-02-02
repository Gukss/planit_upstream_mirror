FROM openjdk:8 AS builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src
RUN chmod =x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:8
COPY --from=builder build/libs/planit-0.0.1-SNAPSHOT.jar planit.jar

EXPOSE 8080

CMD ["java","-jar","planit.jar"]
