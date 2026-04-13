package io.libra.fleet.management.system.core.facade;

import java.util.UUID;
import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import io.libra.fleet.management.system.core.model.request.UpsertVehicleRequest;
import io.libra.fleet.management.system.core.model.response.UpsertVehicleResponse;
import io.libra.fleet.management.system.core.service.VehicleService;
import io.libra.fleet.management.system.core.validation.VehicleValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleFacade {

    private final VehicleValidationService vehicleValidationService;
    private final VehicleService vehicleService;

    public UpsertVehicleResponse upsertVehicle(UpsertVehicleRequest request) {
        vehicleValidationService.validateVehicle(request);

        VehicleEntity vehicleEntity = VehicleEntity.builder()
            .uuid(UUID.randomUUID().toString())
            .make(request.make())
            .model(request.model())
            .registrationNumber(request.registrationNumber())
            .mileage(request.mileage())
            .build();

        vehicleService.persistVehicle(vehicleEntity);
        return UpsertVehicleResponse.builder()
            .id(vehicleEntity.getUuid())
            .build();
    }
}
