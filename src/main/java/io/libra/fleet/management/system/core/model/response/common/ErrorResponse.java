package io.libra.fleet.management.system.core.model.response.common;

import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ErrorResponse {

    private String message;
    private HttpStatus status;
    private ErrorType type;
    private Map<String, String> errors;
}
