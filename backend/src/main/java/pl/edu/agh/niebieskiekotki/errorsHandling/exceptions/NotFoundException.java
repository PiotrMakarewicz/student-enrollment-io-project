package pl.edu.agh.niebieskiekotki.errorsHandling.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends HTTPException {
    public NotFoundException(String message) {
        super(HttpStatus.NOT_FOUND ,message);
    }
}
