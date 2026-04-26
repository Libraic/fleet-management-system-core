package io.libra.fleet.management.system.core.validation;

import static io.libra.fleet.management.system.core.constants.VehicleConstants.VEHICLE_LAST_SERVICE_MILEAGE_REQUIRED_MESSAGE;
import static io.libra.fleet.management.system.core.constants.VehicleConstants.VEHICLE_MAKE_REQUIRED_MESSAGE;
import static io.libra.fleet.management.system.core.constants.VehicleConstants.VEHICLE_MILEAGE_INVALID_MESSAGE;
import static io.libra.fleet.management.system.core.constants.VehicleConstants.VEHICLE_MILEAGE_REQUIRED_MESSAGE;
import static io.libra.fleet.management.system.core.constants.VehicleConstants.VEHICLE_MODEL_REQUIRED_MESSAGE;
import static io.libra.fleet.management.system.core.constants.VehicleConstants.VEHICLE_REGISTRATION_NUMBER_REQUIRED_MESSAGE;
import static io.libra.fleet.management.system.core.constants.VehicleConstants.VEHICLE_SERVICE_INTERVAL_MILEAGE_REQUIRED_MESSAGE;

import java.util.HashMap;
import java.util.Map;

import io.libra.fleet.management.system.core.exception.ErrorField;
import io.libra.fleet.management.system.core.exception.FleetManagementSystemValidationException;
import io.libra.fleet.management.system.core.model.request.UpsertVehicleRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VehicleValidationService {

    public void validateVehicle(UpsertVehicleRequest request) {
        if (!StringUtils.isBlank(request.id())) {
            return;
        }

        log.info("Validating the request for upserting a Vehicle.");

        Map<String, String> errorFields = new HashMap<>();
        if (StringUtils.isBlank(request.make())) {
            errorFields.put(ErrorField.MAKE.getName(), VEHICLE_MAKE_REQUIRED_MESSAGE);
        }

        if (StringUtils.isBlank(request.model())) {
            errorFields.put(ErrorField.MODEL.getName(), VEHICLE_MODEL_REQUIRED_MESSAGE);
        }

        if (StringUtils.isBlank(request.registrationNumber())) {
            errorFields.put(ErrorField.REGISTRATION_NUMBER.getName(), VEHICLE_REGISTRATION_NUMBER_REQUIRED_MESSAGE);
        }

        if (request.mileage() == null) {
            errorFields.put(ErrorField.MILEAGE.getName(), VEHICLE_MILEAGE_REQUIRED_MESSAGE);
        } else if (request.mileage() < 0) {
            errorFields.put(ErrorField.MILEAGE.getName(), VEHICLE_MILEAGE_INVALID_MESSAGE);
        }

        if (request.lastServiceMileage() == null) {
            errorFields.put(ErrorField.LAST_SERVICE_MILEAGE.getName(), VEHICLE_LAST_SERVICE_MILEAGE_REQUIRED_MESSAGE);
        } else if (request.lastServiceMileage() < 0) {
            errorFields.put(ErrorField.LAST_SERVICE_MILEAGE.getName(), VEHICLE_MILEAGE_INVALID_MESSAGE);
        }

        if (request.serviceIntervalMileage() == null) {
            errorFields.put(ErrorField.SERVICE_INTERVAL_MILEAGE.getName(), VEHICLE_SERVICE_INTERVAL_MILEAGE_REQUIRED_MESSAGE);
        } else if (request.serviceIntervalMileage() < 0) {
            errorFields.put(ErrorField.SERVICE_INTERVAL_MILEAGE.getName(), VEHICLE_MILEAGE_INVALID_MESSAGE);
        }

        if (!errorFields.isEmpty()) {
            throw FleetManagementSystemValidationException.ofBadRequest(errorFields);
        }
    }
}
