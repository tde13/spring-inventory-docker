package org.example.inventorydocker.ExceptionHandling;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResponseStatusException.class)
  public ResponseEntity<Map<String, Object>> handleResponseStatusException(ResponseStatusException ex) {
    Map<String, Object> errorBody = new HashMap<>();
    errorBody.put("status", ex.getStatusCode().value());
    errorBody.put("error", ex.getStatusCode().value());
    errorBody.put("message", ex.getReason());

    return new ResponseEntity<>(errorBody, ex.getStatusCode());
  }
}
