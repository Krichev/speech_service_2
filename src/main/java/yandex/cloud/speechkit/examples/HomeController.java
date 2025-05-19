package yandex.cloud.speechkit.examples;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    
    @GetMapping("/")
    @ResponseBody
    public String home() {
        return """
                <html>
                <head>
                    <title>Yandex SpeechKit API</title>
                    <style>
                        body { font-family: Arial, sans-serif; margin: 40px; }
                        .container { max-width: 600px; }
                        h1 { color: #333; }
                        .endpoint { background: #f5f5f5; padding: 10px; margin: 10px 0; border-radius: 5px; }
                        .method { font-weight: bold; color: #2196F3; }
                        .upload-form { background: #fff; padding: 20px; border: 2px dashed #ccc; margin: 20px 0; }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h1>Yandex SpeechKit API v3</h1>
                        <p>Speech Recognition Service is running!</p>
                        
                        <h2>Available Endpoints:</h2>
                        <div class="endpoint">
                            <span class="method">GET</span> /api/speech/health - Health check
                        </div>
                        <div class="endpoint">
                            <span class="method">GET</span> /api/speech/info - Service information
                        </div>
                        <div class="endpoint">
                            <span class="method">POST</span> /api/speech/recognize - Recognize audio (multipart/form-data with 'audio' field)
                        </div>
                        
                        <h2>Test Audio Recognition:</h2>
                        <div class="upload-form">
                            <form action="/api/speech/recognize" method="post" enctype="multipart/form-data">
                                <p>
                                    <label for="audio">Select audio file:</label><br>
                                    <input type="file" name="audio" accept="audio/*" required>
                                </p>
                                <p>
                                    <input type="submit" value="Recognize Speech">
                                </p>
                            </form>
                        </div>
                        
                        <h2>Usage with curl:</h2>
                        <pre style="background: #f5f5f5; padding: 10px; border-radius: 5px;">
curl -X POST \\
  -F "audio=@/path/to/your/audio.wav" \\
  http://localhost:8080/api/speech/recognize
                        </pre>
                    </div>
                </body>
                </html>
                """;
    }
}