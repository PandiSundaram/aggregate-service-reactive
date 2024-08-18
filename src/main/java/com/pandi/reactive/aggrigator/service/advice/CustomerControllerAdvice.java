package com.pandi.reactive.aggrigator.service.advice;

import com.pandi.reactive.aggrigator.service.exception.CustomerNotFoundException;
import com.pandi.reactive.aggrigator.service.exception.CustomerResponseException;
import com.pandi.reactive.aggrigator.service.exception.StockResponseException;
import com.pandi.reactive.aggrigator.service.exception.TradeResponseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomerControllerAdvice {

    @ExceptionHandler(WebExchangeBindException.class)
    public ProblemDetail handleInputValidation(WebExchangeBindException ex) {

        var message = ex.getAllErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.joining(","));
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, message);
        problemDetail.setTitle("INPUT_VALIDATION");
        return problemDetail;

    }


    @ExceptionHandler(CustomerNotFoundException.class)
    public ProblemDetail handleCustomerNotFound(CustomerNotFoundException ex) {

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("CUSTOMER_NOT_FOUND");
        return problemDetail;
    }

    @ExceptionHandler(CustomerResponseException.class)
    public ProblemDetail handleCustomerResponseException(CustomerResponseException ex) {

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("CUSTOMER_ERROR");
        return problemDetail;
    }

    @ExceptionHandler(StockResponseException.class)
    public ProblemDetail handleCustomerResponseException(StockResponseException ex) {

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("STOCK_RESPONSE_ERROR");
        return problemDetail;
    }

    @ExceptionHandler(TradeResponseException.class)
    public ProblemDetail handleCustomerResponseException(TradeResponseException ex) {

        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setTitle("TRADE_RESPONSE_ERROR");
        return problemDetail;
    }

}
