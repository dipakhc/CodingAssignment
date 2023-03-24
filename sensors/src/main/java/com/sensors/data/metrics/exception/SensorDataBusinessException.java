package com.sensors.data.metrics.exception;

public class SensorDataBusinessException extends RuntimeException {

    public SensorDataBusinessException(String message) {
        super(message);
    }

    public SensorDataBusinessException(String message, Throwable t) {
        super(message, t);
    }

    public SensorDataBusinessException() {
    }
}
