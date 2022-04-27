package pl.edu.agh.niebieskiekotki.routes;

public class RequiredFieldIsNullException extends Exception{
    public RequiredFieldIsNullException(String fieldName) {
        super("Field is required : " + fieldName);
    }
}
