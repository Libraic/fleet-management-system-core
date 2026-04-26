package io.libra.fleet.management.system.core.facade;

import java.util.List;
import io.libra.fleet.management.system.core.mapper.VehicleMapper;
import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import io.libra.fleet.management.system.core.model.request.UpsertVehicleRequest;
import io.libra.fleet.management.system.core.model.response.GetVehicleResponse;
import io.libra.fleet.management.system.core.model.response.UpsertVehicleResponse;
import io.libra.fleet.management.system.core.model.response.common.PageResponse;
import io.libra.fleet.management.system.core.service.VehicleService;
import io.libra.fleet.management.system.core.validation.VehicleValidationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleFacade {

    private final VehicleValidationService vehicleValidationService;
    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;

    public UpsertVehicleResponse upsertVehicle(UpsertVehicleRequest request) {
        vehicleValidationService.validateVehicle(request);

        VehicleEntity vehicleEntity = vehicleMapper.fromUpsertRequestToEntity(request);

        vehicleService.persistVehicle(vehicleEntity);

        return vehicleMapper.fromEntityToUpsertResponse(vehicleEntity);
    }

    public PageResponse<GetVehicleResponse> getVehicles(int page) {
        List<VehicleEntity> vehicles = vehicleService.getAllVehicles(page);
        List<GetVehicleResponse> getVehicleResponseList = vehicles.stream()
            .map(vehicleMapper::fromEntityToGetResponse)
            .toList();
        int size = vehicles.size();
        long totalVehicles = vehicleService.countVehicles();
        int totalPages = (int) Math.ceil((double) totalVehicles / size);
        return PageResponse.<GetVehicleResponse>builder()
            .content(getVehicleResponseList)
            .page(page)
            .size(getVehicleResponseList.size())
            .totalElements(totalVehicles)
            .totalPages(totalPages)
            .build();
    }
}
