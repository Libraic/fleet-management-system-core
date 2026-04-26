package io.libra.fleet.management.system.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorField {

    MAKE("make"),
    MODEL("model"),
    REGISTRATION_NUMBER("registrationNumber"),
    MILEAGE("mileage"),
    LAST_SERVICE_MILEAGE("lastServiceMileage"),
    SERVICE_INTERVAL_MILEAGE("serviceIntervalMileage");

    private final String name;
}
