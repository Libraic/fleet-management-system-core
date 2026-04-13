package io.libra.fleet.management.system.core.repository;

import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {
}
