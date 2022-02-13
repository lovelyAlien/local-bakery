ARG PLATFORM=linux/amd64
ARG IMAGE=bakery-api-0.0.1-SNAPSHOT.jar
FROM --platform=${PLATFORM} openjdk:11
COPY ./build/libs/${IMAGE} /usr/app.jar
WORKDIR /usr/
CMD ["java", "-jar", "app.jar"]