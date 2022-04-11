package pl.edu.agh.niebieskiekotki.errorsHandling;

import org.springframework.http.HttpStatus;

public class ErrorMessage {

    HttpStatus status;
    String message;

    public ErrorMessage(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public HttpStatus getStatus(){
        return status;
    }

    public String getMessage() {
        return message;
    }
}
