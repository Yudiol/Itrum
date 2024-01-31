package com.yudiol.itrum.exception;

import com.yudiol.itrum.exception.errors.BadRequestError;
import com.yudiol.itrum.exception.errors.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
@Slf4j
public class GlobalControllerExceptionHandler {
    @ExceptionHandler({BadRequestError.class, MethodArgumentNotValidException.class,
            ConstraintViolationException.class})
    private ResponseEntity<ApiError> handelBadRequestException(RuntimeException e) {
        return getResponseError(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, DateTimeParseException.class})
    private ResponseEntity<ApiError> handelHttpMessageNotReadableException() {
        return getResponseError("Неверная структура объекта: проверьте скобки, запятые, названия полей и параметры",
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return getResponseError("Неверный параметр запроса: проверьте параметры", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<ApiError> handelNotFoundException(NotFoundException e) {
        return getResponseError(e.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler
    private ResponseEntity<ApiError> handelHttpServerErrorException(HttpServerErrorException e) {
        return getResponseError(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ApiError> getResponseError(String exceptionMessage, HttpStatus badRequest) {
        log.error(exceptionMessage);
        ApiError apiError = ApiError.builder()
                .status(badRequest)
                .message(exceptionMessage)
                .date(LocalDateTime.now()).build();
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
