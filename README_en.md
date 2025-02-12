# Recognizing audio with Yandex SpeechKit API v3

This is an example of using Yandex SpeechKit API v3 to recognize audio.

For documentation, refer to [this section](https://cloud.yandex.ru/docs/speechkit/stt/api/streaming-examples-v3).

## Usage

Creating a build:

`mvn clean install`

`cd target`

Save the API key of your service account into the `$API_KEY` environment variable.

`API_KEY=<apikey>`

To run the example, run the following command:

```bash
java -cp speechkit_examples-1.0-SNAPSHOT.jar yandex.cloud.speechkit.examples.SttV3Client <absolut_path_to_wav>
```

The recognition result will be output to the standard output stream, `stdout`.
