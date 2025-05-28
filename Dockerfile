# Використовуємо офіційний образ з Java 21
FROM eclipse-temurin:21-jdk

# Встановлюємо робочий каталог
WORKDIR /app

# Копіюємо всі файли проєкту
COPY . .

# Білдимо проєкт
RUN ./mvnw clean install -DskipTests


# Запускаємо JAR
CMD ["java", "-jar", "target/stationweb-0.0.1-SNAPSHOT.jar"]
