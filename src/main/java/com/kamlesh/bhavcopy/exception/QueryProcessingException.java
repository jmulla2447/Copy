package com.kamlesh.bhavcopy.exception;

import com.kamlesh.bhavcopy.dto.InvalidQueryCommand;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class QueryProcessingException extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        InvalidQueryCommand badEx = new InvalidQueryCommand();
        badEx.setErrMsg("Invalid Command");
        badEx.setBusinessCode("COMMAND_NOT_EXIST_EXCEPTION");
        badEx.setUriInfo(request.getContextPath());

        return new ResponseEntity(badEx, HttpStatus.BAD_REQUEST);
    }
}