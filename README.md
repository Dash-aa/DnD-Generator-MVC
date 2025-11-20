# DnD Character Generator (MVC + Memento)

Це готовий Java Maven-проєкт з графічним інтерфейсом (Swing), архітектурою MVC і реалізацією патерну Memento.
Проєкт генерує персонажів DnD, дозволяє зберігати стан (Memento), відкотити його та експортувати список персонажів у JSON.

## Як зібрати
Потрібен встановлений Java 11+ та Maven.

```bash
mvn clean package
# Згенерує jar-with-dependencies у папці target/
java -jar target/dnd-generator-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Функціонал
- Введення імені персонажа
- Вибір раси та класу зі списку
- Генерація атрибутів (Stats)
- Збереження стану (Save State) і відкат (Restore State) — Memento
- Додавання персонажа в список і експорт у JSON
