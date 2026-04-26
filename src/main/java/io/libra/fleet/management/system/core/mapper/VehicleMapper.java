package io.libra.fleet.management.system.core.mapper;

import java.util.UUID;

import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import io.libra.fleet.management.system.core.model.request.UpsertVehicleRequest;
import io.libra.fleet.management.system.core.model.response.GetVehicleResponse;
import io.libra.fleet.management.system.core.model.response.UpsertVehicleResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMapper {

    public VehicleEntity fromUpsertRequestToEntity(UpsertVehicleRequest request) {
        return VehicleEntity.builder()
            .uuid(UUID.randomUUID().toString())
            .make(request.make())
            .model(request.model())
            .registrationNumber(request.registrationNumber())
            .mileage(request.mileage())
            .lastServiceMileage(request.lastServiceMileage())
            .serviceIntervalMileage(request.serviceIntervalMileage())
            .build();
    }

    public UpsertVehicleResponse fromEntityToUpsertResponse(VehicleEntity entity) {
        return UpsertVehicleResponse.builder()
            .id(entity.getUuid())
            .build();
    }

    public GetVehicleResponse fromEntityToGetResponse(VehicleEntity entity) {
        return GetVehicleResponse.builder()
            .id(entity.getUuid())
            .make(entity.getMake())
            .model(entity.getModel())
            .registrationNumber(entity.getRegistrationNumber())
            .mileage(entity.getMileage())
            .lastServiceMileage(entity.getLastServiceMileage())
            .serviceIntervalMileage(entity.getServiceIntervalMileage())
            .build();
    }
}
