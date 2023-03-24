package com.sensors.data.metrics.controller;

import com.sensors.data.metrics.exception.SensorDataBusinessException;
import com.sensors.data.metrics.exception.SensorDataSystemException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Controller to handle the exceptions that are thrown by the application during calls to the REST controllers
 */
@ControllerAdvice
public class SensorDataExceptionController extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LogManager.getFormatterLogger(SensorDataExceptionController.class);

    private static final String UNEXPECTED_PROBLEM =
            "An unexpected problem has occurred, if this issue persists please contact the HelpDesk.";

    private static final String MESSAGE_KEY = "message";

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<Object> handleUnexpectedException(Exception ex, WebRequest request) {
        LOGGER.error("An unexpected exception has occurred " + ex);
        Map<String, Object> exceptionWrapper = new HashMap<>();

        if (Objects.nonNull(ex.getMessage())) {
            exceptionWrapper.put(MESSAGE_KEY, ex.getMessage());
        } else {
            exceptionWrapper.put(MESSAGE_KEY, UNEXPECTED_PROBLEM);
        }

        return handleExceptionInternal(ex, exceptionWrapper, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(value = SensorDataSystemException.class)
    protected ResponseEntity<Object> handleSystemException(SensorDataSystemException ex, WebRequest request) {
        LOGGER.error("Failed to process web request " + ex.getMessage());
        Map<String, Object> sensorDataSystemExceptionWrapper = new HashMap<>();
        if (Objects.nonNull(ex.getMessage())) {
            sensorDataSystemExceptionWrapper.put(MESSAGE_KEY, ex.getMessage());
        } else {
            sensorDataSystemExceptionWrapper.put(MESSAGE_KEY, UNEXPECTED_PROBLEM);
        }

        return handleExceptionInternal(ex, sensorDataSystemExceptionWrapper, new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }


    @ExceptionHandler(value = SensorDataBusinessException.class)
    protected ResponseEntity<Object> handleBusinessValidationException(SensorDataBusinessException ex, WebRequest request) {
        LOGGER.error("Failed to process web request " + ex.getMessage());
        Map<String, Object> sensorDataBusinessExceptionWrapper = new HashMap<>();
        if (Objects.nonNull(ex.getMessage())) {
            sensorDataBusinessExceptionWrapper.put(MESSAGE_KEY, ex.getMessage());
        } else {
            sensorDataBusinessExceptionWrapper.put(MESSAGE_KEY, UNEXPECTED_PROBLEM);
        }
        return handleExceptionInternal(ex, sensorDataBusinessExceptionWrapper, new HttpHeaders(), HttpStatus.BAD_REQUEST,
                request);
    }
}

