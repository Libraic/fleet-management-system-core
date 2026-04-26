package io.libra.fleet.management.system.core.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class VehicleConstants {

    public static final String VEHICLE_MAKE_REQUIRED_MESSAGE = "Marca este obligatorie";
    public static final String VEHICLE_MODEL_REQUIRED_MESSAGE = "Modelul este obligatoriu";
    public static final String VEHICLE_REGISTRATION_NUMBER_REQUIRED_MESSAGE = "Numărul de înmatriculare este obligatoriu";
    public static final String VEHICLE_MILEAGE_REQUIRED_MESSAGE = "Parcursul este obligatoriu";
    public static final String VEHICLE_MILEAGE_INVALID_MESSAGE = "Formatul parcursului este invalid";
    public static final String VEHICLE_LAST_SERVICE_MILEAGE_REQUIRED_MESSAGE = "Parcursul la ultima revizie este obligatoriu";
    public static final String VEHICLE_SERVICE_INTERVAL_MILEAGE_REQUIRED_MESSAGE = "Intervalul de revizie este obligatoriu";
}
