# 1. Вибираємо образ із підтримкою Java 21
FROM eclipse-temurin:21-jdk AS build

# 2. Копіюємо всі файли у внутрішню директорію контейнера
WORKDIR /app
COPY . .

# 3. Робимо Maven Wrapper виконуваним
RUN chmod +x mvnw

# 4. Збираємо проєкт без запуску тестів
RUN ./mvnw clean install -DskipTests

# --- Фінальний контейнер без зайвого (опційно multi-stage) ---
FROM eclipse-temurin:21-jdk

# Директорія для запуску
WORKDIR /app

# Копіюємо зібраний `.jar` файл з попереднього етапу
COPY --from=build /app/target/*.jar app.jar

# Команда запуску
CMD ["java", "-jar", "app.jar"]
