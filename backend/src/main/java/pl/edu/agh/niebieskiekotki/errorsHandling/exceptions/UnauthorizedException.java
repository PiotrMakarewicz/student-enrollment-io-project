package pl.edu.agh.niebieskiekotki.errorsHandling.exceptions;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends HTTPException{
    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED, message);
    }
}
