FROM openjdk:11
MAINTAINER Renan Zazula

RUN mkdir -p /inAtlasCoffeeShop-app/

ADD target/inAtlasCoffeeShop-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar", "app.jar"]

EXPOSE 8080
