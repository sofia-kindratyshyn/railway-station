# Базовий образ з Java 17
FROM eclipse-temurin:17-jdk

# Робоча директорія
WORKDIR /app

# Копіюємо весь проект у контейнер
COPY . .

# Робимо mvnw виконуваним
RUN chmod +x mvnw

# Скачуємо залежності і збираємо проєкт (опціонально без тестів для пришвидшення)
RUN ./mvnw clean install -DskipTests

# Запускаємо додаток
CMD ["./mvnw", "spring-boot:run"]
