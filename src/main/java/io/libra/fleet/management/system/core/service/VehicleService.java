package io.libra.fleet.management.system.core.service;

import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import io.libra.fleet.management.system.core.repository.VehicleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public void persistVehicle(VehicleEntity vehicleEntity) {
        log.debug("Persisting the Vehicle with UUID=[{}].", vehicleEntity.getUuid());
        vehicleRepository.save(vehicleEntity);
    }
}
