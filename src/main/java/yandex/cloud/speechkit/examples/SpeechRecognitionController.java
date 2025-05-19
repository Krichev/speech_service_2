package yandex.cloud.speechkit.examples;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

@RestController
@RequestMapping("/api/speech")
@CrossOrigin(origins = "*") // Allow CORS for frontend integration
public class SpeechRecognitionController {
    
    private final SpeechRecognitionService speechRecognitionService;
    
    @Autowired
    public SpeechRecognitionController(SpeechRecognitionService speechRecognitionService) {
        this.speechRecognitionService = speechRecognitionService;
    }
    
    @PostMapping(value = "/recognize", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<RecognitionResponse> recognizeAudio(
            @RequestParam("audio") MultipartFile audioFile) {
        
        try {
            // Validate file
            if (audioFile.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(new RecognitionResponse("Audio file is empty", false));
            }
            
            // Check file type (optional - you might want to be more strict)
            String contentType = audioFile.getContentType();
            if (contentType != null && !contentType.startsWith("audio/")) {
                // Allow it through anyway, as content-type detection can be unreliable
                System.out.println("Warning: Uploaded file content type is " + contentType);
            }
            
            // Process the audio
            String recognizedText = speechRecognitionService.recognizeAudio(audioFile);
            
            if (recognizedText == null || recognizedText.trim().isEmpty()) {
                return ResponseEntity.ok(new RecognitionResponse("No speech recognized", true));
            }
            
            return ResponseEntity.ok(new RecognitionResponse(recognizedText.trim()));
            
        } catch (UnsupportedAudioFileException e) {
            return ResponseEntity.badRequest()
                    .body(new RecognitionResponse("Unsupported audio format: " + e.getMessage(), false));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RecognitionResponse("Error processing audio file: " + e.getMessage(), false));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RecognitionResponse("Configuration error: " + e.getMessage(), false));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new RecognitionResponse("Unexpected error: " + e.getMessage(), false));
        }
    }
    
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Speech Recognition Service is running");
    }
    
    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok(
                "Yandex SpeechKit API v3 Recognition Service\n" +
                "Supported formats: WAV, MP3, OGG\n" +
                "Usage: POST /api/speech/recognize with multipart form data containing 'audio' file"
        );
    }
}