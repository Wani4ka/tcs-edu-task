package ru.tinkoff.edu.java.scrapper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.tinkoff.edu.java.scrapper.dto.ApiErrorResponse;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ScrapperControllerExceptionHandler {

    private ApiErrorResponse constructResponse(Exception ex, HttpStatus code) {
        return new ApiErrorResponse(
                ex.getMessage(), String.valueOf(code.value()),
                ex.getClass().getSimpleName(), ex.getMessage(),
                Arrays.stream(ex.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList())
        );
    }

    @ExceptionHandler({ IllegalArgumentException.class, MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorResponse handleBadRequest(Exception ex) {
        return constructResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ IndexOutOfBoundsException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrorResponse handleNotFound(Exception ex) {
        return constructResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorResponse handleException(Exception ex) {
        return constructResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
