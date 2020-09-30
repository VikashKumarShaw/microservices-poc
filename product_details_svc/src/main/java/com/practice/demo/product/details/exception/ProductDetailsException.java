package com.practice.demo.product.details.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.practice.demo.product.details.models.DetailResponse;

@ControllerAdvice
public class ProductDetailsException extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        DetailResponse error = new DetailResponse(HttpStatus.INTERNAL_SERVER_ERROR,"Server Error", details);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
 
    @ExceptionHandler(ProductDoesNotExistException.class)
    public final ResponseEntity<Object> handleUserNotFoundException1(ProductDoesNotExistException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        DetailResponse error = new DetailResponse(HttpStatus.NOT_FOUND,"Product does not exist", details);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for(ObjectError error : ex.getBindingResult().getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        DetailResponse error = new DetailResponse(HttpStatus.BAD_REQUEST,"Validation Failed", details);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
