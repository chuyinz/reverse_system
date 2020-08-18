package com.example.cms.handler;

import com.example.cms.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController

public class GlobalExceptionHandler {

    @ExceptionHandler(value = UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse userNotFoundExceptionHandler()
    {
        ExceptionResponse response=new  ExceptionResponse("用户没有找到！");
        return response;
    }

    @ExceptionHandler(value = InvalidOperationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse invalidOperationExceptionHandler(InvalidOperationException es)
    {
        String mes=es.getMessage();
        ExceptionResponse response=new  ExceptionResponse(mes);
        return response;
    }

    @ExceptionHandler(value = AccessDenyException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponse AccessDenyExceptionHandler(AccessDenyException es)
    {
        String mes=es.getMessage();
        ExceptionResponse response=new  ExceptionResponse(mes);
        return response;
    }

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse NotFoundExceptionHandler(NotFoundException es)
    {
        String mes=es.getMessage();
        ExceptionResponse response=new  ExceptionResponse(mes);
        return response;
    }
}

