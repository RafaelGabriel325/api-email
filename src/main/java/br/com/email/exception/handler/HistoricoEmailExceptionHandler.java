package br.com.email.exception.handler;


import br.com.email.exception.ErrorResponse;
import br.com.email.exception.HistoricoEmailException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class HistoricoEmailExceptionHandler {
    @ExceptionHandler(HistoricoEmailException.class)
    public final ResponseEntity<ErrorResponse> handleHistoricoEmailException(Exception exception, WebRequest request) {
        ErrorResponse exceptionResponse = new ErrorResponse(
                LocalDateTime.now(),
                exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
