package pl.edu.agh.niebieskiekotki.errorsHandling.exceptions;

import org.springframework.http.HttpStatus;

public class InvalidPasswordException extends HTTPException{

    public InvalidPasswordException(String message) {
        super(HttpStatus.FORBIDDEN, message);
    }
}
