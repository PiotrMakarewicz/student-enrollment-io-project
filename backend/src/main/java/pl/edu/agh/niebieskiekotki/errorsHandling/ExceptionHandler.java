package pl.edu.agh.niebieskiekotki.errorsHandling;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.HTTPException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(HTTPException.class)


    public ResponseEntity<ErrorMessage> HandleNotFound(HTTPException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getStatusCode(), exception.getMessage());
        return new ResponseEntity<>(errorMessage, exception.getStatusCode());
    }

}
