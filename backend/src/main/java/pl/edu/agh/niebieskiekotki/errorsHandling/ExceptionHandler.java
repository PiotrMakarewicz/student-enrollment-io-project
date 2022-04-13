package pl.edu.agh.niebieskiekotki.errorsHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.edu.agh.niebieskiekotki.errorsHandling.exceptions.NotFoundException;

import java.io.IOException;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {
//    @org.springframework.web.bind.annotation.ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ErrorMessage> HandleNotFound(NotFoundException exception) throws IOException{
//
//        ErrorMessage errorMessage = new ErrorMessage( HttpStatus.NOT_FOUND, exception.getMessage());
//        return new ResponseEntity(errorMessage, HttpStatus.NOT_FOUND);
//    }
}
