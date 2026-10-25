# Модуль 2 — Jetpack Compose

Учебный Android-проект с решениями заданий 1–5 и 10. Для варианта 12 в предоставленных условиях отдельных требований нет.

## Запуск

Откройте папку проекта в Android Studio. Нужны Android SDK Platform 35, Build Tools 35.0.0 и JDK 21. Gradle Wrapper загрузит Gradle 8.13. В SDK Manager установите платформу Android 35. Локальный путь к SDK Android Studio запишет в `local.properties`; этот файл в репозитории не хранится.

Для задания 1 есть две независимо собираемые версии:

- `withNameDebug`: в `Hello` передаётся строка `Алексей`;
- `withoutNameDebug`: в `Hello` передаётся `null`.

Сборка в PowerShell при настроенном `JAVA_HOME`:

```powershell
.\gradlew.bat :app:assembleWithNameDebug :app:assembleWithoutNameDebug
```

APK находятся в `app/build/outputs/apk/withName/debug/` и `app/build/outputs/apk/withoutName/debug/`. Минимальная версия Android — 9 (API 28). У версий разные идентификаторы пакета, поэтому их можно установить одновременно.

На экране приложения есть кнопки перехода к заданиям 1, 3, 4, 5 и 10. Задание 2 — три предпросмотра функции `HelloPreview` в файле `HelloPreview.kt`. Каждый экран — самостоятельный пример; при уходе с экрана корзины и возвращении она создаётся заново.

## Файлы решений

| Задание | Файл |
|---|---|
| 1 | `Hello.kt`, вызов в `MainActivity.kt`, две сборки в `app/build.gradle.kts` |
| 2 | `HelloPreview.kt` |
| 3 | `TextSamples.kt`, строка в `res/values/strings.xml` |
| 4 | `StyledButton.kt` |
| 5 | `Initials.kt` |
| 10 | `Cart.kt`, `ShoppingCart.kt` |

Все Kotlin-файлы решений находятся в `app/src/main/java/ru/mirea/module2/`.

## Проверка

```powershell
.\gradlew.bat :app:testWithNameDebugUnitTest :app:lintWithNameDebug
.\gradlew.bat :app:connectedWithNameDebugAndroidTest
```

Вторая команда требует запущенного Android-эмулятора или подключённого устройства. Семь модульных тестов проверяют суммы, границу 500/501, добавление, удаление, пустой список, нулевую цену и сериализацию состояния. Четыре теста интерфейса проверяют начальные значения, пустую корзину, восстановление `rememberSaveable` и обработку `null`.

Для формирования настоящих изображений предпросмотра используется официальный инструмент Compose Preview Screenshot Testing с Layoutlib:

```powershell
.\gradlew.bat :app:updateWithNameDebugScreenshotTest
.\gradlew.bat :app:validateWithNameDebugScreenshotTest
```

Конфигурации в `app/src/screenshotTest/` повторяют три настройки `HelloPreview` и предпросмотр текстовых элементов. Эталонные изображения находятся в `app/src/screenshotTestWithNameDebug/reference/`.

## Исправления корзины

Сумма вычисляется из списка товаров через `sumOf`, количество — через `size`. Отрисовка ничего не прибавляет к этим значениям. Добавление и удаление создают новое состояние. Toast вызывается только в обработчике добавления, если новая сумма больше 500. Для сохранения небольшого учебного состояния при пересоздании Activity используются `rememberSaveable` и сериализуемые классы `Cart` и `Product`.

## Версии

Kotlin 2.2.10, Android Gradle Plugin 8.13.2, Gradle 8.13, Compose BOM 2024.09.00, Compose Preview Screenshot Testing 0.0.1-alpha16. Интерфейс реализован на Material 3.
