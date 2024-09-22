[![Java CI with Gradle](https://github.com/ischeglov/QA_TAGES/actions/workflows/gradle.yml/badge.svg)](https://github.com/ischeglov/QA_TAGES/actions/workflows/gradle.yml)

## Задача

Покрыть автотестами главную страницу [TAGES](https://tages.ru/)

## Тестовая документация:

1. [План автоматизации тестирования](documentation/Plan.md)
2. [Отчет по итогам тестирования](documentation/Report.md)
3. [Отчет по итогам автоматизации](documentation/Allure.md)

## Шаги для воспроизведения:

### Подготовительный этап

1. Установить и запустить [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/download/);
2. Склонировать репозиторий с Github командой через терминал:
```
git clone git@github.com:ischeglov/QA_TAGES.git
```
3. Открыть проект в IntelliJ IDEA.

### Запуск тестов

4. В новой вкладке терминала запустить тесты:
 ```
./gradlew clean test
```

### Формирование отчёта

5. Для формирования отчета в новой вкладке терминала ввести команду
```
./gradlew allureServe
```
