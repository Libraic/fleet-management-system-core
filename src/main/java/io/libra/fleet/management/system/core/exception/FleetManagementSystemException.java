package io.libra.fleet.management.system.core.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FleetManagementSystemException extends RuntimeException {

    private final HttpStatus httpStatus;

    private FleetManagementSystemException(
        String message,
        HttpStatus httpStatus
    ) {
        super(message);
        this.httpStatus = httpStatus;
    }

    public static FleetManagementSystemException of(String message, HttpStatus httpStatus) {
        return new FleetManagementSystemException(message, httpStatus);
    }

    public static FleetManagementSystemException ofInternal(String message) {
        return new FleetManagementSystemException(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static FleetManagementSystemException ofBadRequest(String message) {
        return new FleetManagementSystemException(message, HttpStatus.BAD_REQUEST);
    }
}
