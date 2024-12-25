package com.bibebazaz.parking_management_service.api.exceptions;

public class ParkingException extends RuntimeException {
    private final String message;

    public ParkingException(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
