# Модуль 2 — Jetpack Compose

Учебный Android-проект с решениями всех десяти заданий модуля 2. Для варианта 12 в условиях отдельных требований нет.

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

В приложении есть экраны заданий 1, 3–8 и 10. В задании 7 кнопки «Контакт», «Список» и «Строка» переключают три раскладки. Задание 2 выполнено в Compose Preview, а задание 9 состоит из UI-тестов для трекера воды.

## Файлы решений

| Задание | Файл |
|---|---|
| 1 | `Hello.kt`, вызов в `MainActivity.kt`, две сборки в `app/build.gradle.kts` |
| 2 | `HelloPreview.kt` |
| 3 | `TextSamples.kt`, строка в `res/values/strings.xml` |
| 4 | `StyledButton.kt` |
| 5 | `Initials.kt` |
| 6 | `CircleExamples.kt`, вектор `res/drawable/circle.xml` |
| 7 | `ContactLayouts.kt`, вектор звезды `res/drawable/favorite_star.xml` |
| 8 | `WaterTracker.kt` |
| 9 | `app/src/androidTest/java/ru/mirea/module2/WaterTrackerTest.kt` |
| 10 | `Cart.kt`, `ShoppingCart.kt` |

Файлы экранов и компонентов находятся в `app/src/main/java/ru/mirea/module2/`.

## Проверка

```powershell
.\gradlew.bat :app:testWithNameDebugUnitTest :app:lintWithNameDebug
.\gradlew.bat :app:connectedWithNameDebugAndroidTest
```

UI-тесты запускаются на Android-эмуляторе. Семь модульных тестов проверяют корзину, четыре прежних UI-теста проверяют корзину и приветствие. Ещё четыре UI-теста проверяют добавление воды по 250 мл, завершение дня, серию при объёмах 1500, 1750 и 1250 мл и восстановление данных после пересоздания Activity.

Запуск только тестов задания 9:

```powershell
.\gradlew.bat :app:connectedWithNameDebugAndroidTest '-Pandroid.testInstrumentationRunnerArguments.class=ru.mirea.module2.WaterTrackerTest'
```

Тесты трекера начинают каждый сценарий с чистых настроек трекера в сборке `withNameDebug`.

Для формирования настоящих изображений предпросмотра используется официальный инструмент Compose Preview Screenshot Testing с Layoutlib:

```powershell
.\gradlew.bat :app:updateWithNameDebugScreenshotTest
.\gradlew.bat :app:validateWithNameDebugScreenshotTest
```

Конфигурации в `app/src/screenshotTest/` повторяют три настройки `HelloPreview` и предпросмотр текстовых элементов. Эталонные изображения находятся в `app/src/screenshotTestWithNameDebug/reference/`.

## Трекер воды

При первом открытии на счётчике 100 мл, как в заготовке задания. Кнопка «+250 мл» прибавляет 250 мл к текущему значению. «Завершить день» сбрасывает дневной объём до нуля: при 1500 мл и больше серия увеличивается на день, при меньшем объёме обнуляется. Объём и серия хранятся в локальных `SharedPreferences` и восстанавливаются при следующем открытии приложения.

## Исправления корзины

Сумма вычисляется из списка товаров через `sumOf`, количество — через `size`. Отрисовка ничего не прибавляет к этим значениям. Добавление и удаление создают новое состояние. Toast вызывается только в обработчике добавления, если новая сумма больше 500. Для сохранения небольшого учебного состояния при пересоздании Activity используются `rememberSaveable` и сериализуемые классы `Cart` и `Product`.

## Версии

Kotlin 2.2.10, Android Gradle Plugin 8.13.2, Gradle 8.13, Compose BOM 2024.09.00, Compose Preview Screenshot Testing 0.0.1-alpha16. Интерфейс реализован на Material 3.
