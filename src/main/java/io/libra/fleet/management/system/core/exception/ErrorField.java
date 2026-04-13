package io.libra.fleet.management.system.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorField {

    MAKE("make"),
    MODEL("model"),
    REGISTRATION_NUMBER("registrationNumber"),
    MILEAGE("mileage");

    private final String name;
}
