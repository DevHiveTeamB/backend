package com.devhive03.Controller.ExceptionControll;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> fieldValueException(BindingResult bindingResult) {

        List<ObjectError> allErrors = bindingResult.getAllErrors();

        Map<String, String> map = new HashMap<>();
        allErrors.forEach(error -> {
            FieldError fieldError = (FieldError) error;
            String fieldName = fieldError.getField();
            String message = fieldError.getDefaultMessage();
            map.put(fieldName, message);
        });

        return ResponseEntity.badRequest()
                .body(map);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,String>> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity.status(400).body(map);
    }
    @ExceptionHandler(FileIsNotIOException.class)
    public ResponseEntity<Map<String,String>> fileIsNotIOException(FileIsNotIOException e, HttpServletRequest request) {
        HashMap<String, String> map = new HashMap<>();
        map.put("message", e.getMessage());
        return ResponseEntity.status(400).body(map);
    }

}