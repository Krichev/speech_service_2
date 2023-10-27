# Распознавание аудио с помощью Yandex SpeechKit API v3

Пример использования Yandex SpeechKit API v3 на Java для распознавания аудио.

Документация: https://cloud.yandex.ru/docs/speechkit/stt/api/streaming-examples-v3

## Использование

Сборка:

`mvn clean install`

`cd target`

В переменную окружения `$API_KEY` сохраните API-ключ сервисного аккаунта.

`API_KEY=<apikey>`

Чтобы запустить пример, выполните команду:


```bash
java -cp speechkit_examples-1.0-SNAPSHOT.jar yandex.cloud.speechkit.examples.SttV3Client <absolut_path_to_wav>
```

Результат распознавания будет выведен в стандартный поток вывода stdout.
