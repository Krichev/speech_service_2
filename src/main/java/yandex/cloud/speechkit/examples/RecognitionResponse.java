package yandex.cloud.speechkit.examples;

public class RecognitionResponse {
    private String recognizedText;
    private boolean success;
    private String errorMessage;
    
    public RecognitionResponse() {
    }
    
    public RecognitionResponse(String recognizedText) {
        this.recognizedText = recognizedText;
        this.success = true;
    }
    
    public RecognitionResponse(String errorMessage, boolean success) {
        this.errorMessage = errorMessage;
        this.success = success;
    }
    
    // Getters and setters
    public String getRecognizedText() {
        return recognizedText;
    }
    
    public void setRecognizedText(String recognizedText) {
        this.recognizedText = recognizedText;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getErrorMessage() {
        return errorMessage;
    }
    
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
    @Override
    public String toString() {
        return "RecognitionResponse{" +
                "recognizedText='" + recognizedText + '\'' +
                ", success=" + success +
                ", errorMessage='" + errorMessage + '\'' +
                '}';
    }
}