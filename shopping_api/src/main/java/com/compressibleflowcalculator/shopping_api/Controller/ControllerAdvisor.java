package com.compressibleflowcalculator.shopping_api.Controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.compressibleflowcalculator.shopping_api.Controller.Responses.Exceptions.KafkaConnectionError;
import com.compressibleflowcalculator.shopping_api.Controller.Responses.Group.InvalidInviteException;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleCityNotFoundException(
            Exception ex, WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "City not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidInviteException.class)
    public ResponseEntity<Object> handleInvalidInviteException(
            Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Invalid Invite Code");

        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(KafkaConnectionError.class)
    public ResponseEntity<Object> handleKafkaException(
            Exception ex, WebRequest req) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", "Failed To Connect To Kafka");

        return new ResponseEntity<Object>(body, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<Object> handleDateParseException(
            Exception ex, WebRequest req) {
        Map<String, Object> body = new HashMap<>();

        body.put("timestamp", LocalDateTime.now());
        body.put("message", "INvalid Date Time");

        return new ResponseEntity<Object>(body, HttpStatus.BAD_GATEWAY);
    }

}
