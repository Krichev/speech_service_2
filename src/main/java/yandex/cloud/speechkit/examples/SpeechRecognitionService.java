package yandex.cloud.speechkit.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class SpeechRecognitionService {

    private final SpeechKitProperties properties;

    @Autowired
    public SpeechRecognitionService(SpeechKitProperties properties) {
        this.properties = properties;
    }

    public String recognizeAudio(MultipartFile audioFile) throws IOException, UnsupportedAudioFileException {
        // Create temporary file to store uploaded audio
        Path tempFile = Files.createTempFile("audio_", ".wav");

        try {
            // Copy uploaded file to temporary file
            Files.copy(audioFile.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

            // Get API key from properties or environment variable
            String apiKey = properties.getApiKey();
            if (apiKey == null || apiKey.trim().isEmpty()) {
                apiKey = System.getenv("API_KEY");
            }

            if (apiKey == null || apiKey.trim().isEmpty()) {
                throw new IllegalStateException("API key is not configured. Set it in application.properties or as environment variable API_KEY");
            }

            // Create SttV3Client and recognize audio
            SttV3Client client = new SttV3Client(properties.getHost(), properties.getPort(), apiKey);
            return client.recognize(tempFile.toFile());

        } finally {
            // Clean up temporary file
            try {
                Files.deleteIfExists(tempFile);
            } catch (IOException e) {
                // Log error but don't throw exception
                System.err.println("Failed to delete temporary file: " + e.getMessage());
            }
        }
    }

    public String recognizeAudioFromFile(File audioFile) throws IOException, UnsupportedAudioFileException {
        // Get API key from properties or environment variable
        String apiKey = properties.getApiKey();
        if (apiKey == null || apiKey.trim().isEmpty()) {
            apiKey = System.getenv("API_KEY");
        }

        if (apiKey == null || apiKey.trim().isEmpty()) {
            throw new IllegalStateException("API key is not configured. Set it in application.properties or as environment variable API_KEY");
        }

        // Create SttV3Client and recognize audio
        SttV3Client client = new SttV3Client(properties.getHost(), properties.getPort(), apiKey);
        return client.recognize(audioFile);
    }
}