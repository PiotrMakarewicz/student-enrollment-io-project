package pl.edu.agh.niebieskiekotki.errorsHandling.exceptions;

import org.springframework.http.HttpStatus;

public class HTTPException extends Exception{
    HttpStatus statusCode;

    HTTPException(HttpStatus statusCode, String message){
        super(message);
        this.statusCode = statusCode;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }
}
