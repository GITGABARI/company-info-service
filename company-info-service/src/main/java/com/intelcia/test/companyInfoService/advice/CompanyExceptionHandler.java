package com.intelcia.test.companyInfoService.advice;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

@RestControllerAdvice
public class CompanyExceptionHandler {
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	public String handleInvalidArgument(ConstraintViolationException ex ){
		return ex.getMessage();
		
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
    @ResponseStatus(value= HttpStatus.NOT_FOUND)
    public String requestHandlingNoHandlerFound(HttpClientErrorException ex) {
		return ex.getMessage();
        
    }

}

