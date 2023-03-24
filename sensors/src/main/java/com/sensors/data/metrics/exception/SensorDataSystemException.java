package com.sensors.data.metrics.exception;

public class SensorDataSystemException extends RuntimeException {
    public SensorDataSystemException(String message) {
        super(message);
    }

    public SensorDataSystemException(String message, Throwable t) {
        super(message, t);
    }

    public SensorDataSystemException() {
    }
}
