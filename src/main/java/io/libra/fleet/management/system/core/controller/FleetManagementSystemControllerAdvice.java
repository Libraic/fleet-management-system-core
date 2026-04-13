package io.libra.fleet.management.system.core.controller;

import static io.libra.fleet.management.system.core.constants.ErrorConstants.INTERNAL_SERVER_ERROR_MESSAGE;
import static io.libra.fleet.management.system.core.model.response.common.ErrorType.GENERAL;
import static io.libra.fleet.management.system.core.model.response.common.ErrorType.SYSTEM;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import ch.qos.logback.core.util.StringUtil;
import java.sql.SQLException;
import io.libra.fleet.management.system.core.exception.FleetManagementSystemException;
import io.libra.fleet.management.system.core.exception.FleetManagementSystemValidationException;
import io.libra.fleet.management.system.core.model.response.common.ErrorResponse;
import io.libra.fleet.management.system.core.model.response.common.ErrorType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class FleetManagementSystemControllerAdvice {

    @ExceptionHandler(FleetManagementSystemException.class)
    public ResponseEntity<ErrorResponse> handleGenericException(FleetManagementSystemException ex) {
        logException(ex);
        var errorResponse = ErrorResponse.builder()
            .message(ex.getLocalizedMessage())
            .type(GENERAL)
            .status(ex.getHttpStatus())
            .build();
        return ResponseEntity
            .status(ex.getHttpStatus())
            .body(errorResponse);
    }

    @ExceptionHandler(FleetManagementSystemValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(FleetManagementSystemValidationException ex) {
        logException(ex);
        var errorResponse = ErrorResponse.builder()
            .message(ex.getLocalizedMessage())
            .type(ErrorType.VALIDATION)
            .status(ex.getHttpStatus())
            .errors(ex.getErrors())
            .build();
        return ResponseEntity
            .status(ex.getHttpStatus())
            .body(errorResponse);
    }

    @ExceptionHandler({ IllegalArgumentException.class, NullPointerException.class })
    public ResponseEntity<ErrorResponse> handleNativeExceptions(RuntimeException ex) {
        logException(ex);
        String exceptionMessage = ex.getLocalizedMessage();
        String message = StringUtil.isNullOrEmpty(exceptionMessage) ? INTERNAL_SERVER_ERROR_MESSAGE : exceptionMessage;
        var errorResponse = ErrorResponse.builder()
            .message(message)
            .status(INTERNAL_SERVER_ERROR)
            .type(SYSTEM)
            .build();
        return ResponseEntity
            .status(INTERNAL_SERVER_ERROR)
            .body(errorResponse);
    }

    @ExceptionHandler({ SQLException.class })
    public ResponseEntity<ErrorResponse> handleSqlExceptions(Exception sqlException) {
        logException(sqlException);
        var errorResponse = ErrorResponse.builder()
            .message(INTERNAL_SERVER_ERROR_MESSAGE)
            .status(INTERNAL_SERVER_ERROR)
            .type(SYSTEM)
            .build();
        return ResponseEntity
            .status(INTERNAL_SERVER_ERROR)
            .body(errorResponse);
    }

    private void logException(Exception ex) {
        log.error("An internal error has occurred: ", ex);
    }
}
