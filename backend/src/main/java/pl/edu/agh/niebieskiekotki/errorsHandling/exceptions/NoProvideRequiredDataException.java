package pl.edu.agh.niebieskiekotki.errorsHandling.exceptions;


import org.springframework.http.HttpStatus;

public class NoProvideRequiredDataException extends HTTPException {
    public NoProvideRequiredDataException(String message) {
        super(HttpStatus.BAD_REQUEST ,message);
    }
}


