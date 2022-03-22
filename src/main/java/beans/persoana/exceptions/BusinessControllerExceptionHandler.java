package beans.persoana.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice

public class BusinessControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(MyBusinessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(MyBusinessException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
