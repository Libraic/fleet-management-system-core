package io.libra.fleet.management.system.core.exception;

import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class FleetManagementSystemValidationException extends RuntimeException {

    private Map<String, String> errors;
    private HttpStatus httpStatus;

    public static FleetManagementSystemValidationException ofBadRequest(Map<String, String> errors) {
        return new FleetManagementSystemValidationException(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    public String toString() {
        return "FleetManagementSystemValidationException{" +
            "errors=" + errors +
            ", httpStatus=" + httpStatus +
            '}';
    }
}
