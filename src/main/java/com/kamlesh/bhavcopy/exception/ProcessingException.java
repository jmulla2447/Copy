package com.kamlesh.bhavcopy.exception;

import com.kamlesh.bhavcopy.dto.FileLoadException;
import com.kamlesh.bhavcopy.dto.InvalidQueryCommand;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class ProcessingException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        InvalidQueryCommand badEx = new InvalidQueryCommand();
        badEx.setErrMsg("Invalid Command");
        badEx.setBusinessCode("COMMAND_NOT_EXIST_EXCEPTION");
        badEx.setUriInfo(request.getContextPath());

        logger.warn("Invalid command received: {}", ex);

        return new ResponseEntity<>(badEx, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileLoadException.class)
    public ResponseEntity<String> handleFileLoadException(FileLoadException ex, WebRequest request) {
        logger.error("File loading error occurred: {}", ex);

        Throwable cause = ex.getCause();
        if (cause instanceof IOException) {
            return new ResponseEntity<>("Error reading file: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (cause instanceof CsvValidationException) {
            return new ResponseEntity<>("CSV Validation Error: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>("File loading error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
