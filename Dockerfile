FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

EXPOSE 3000

ENTRYPOINT ["tail", "-f", "/dev/null"]