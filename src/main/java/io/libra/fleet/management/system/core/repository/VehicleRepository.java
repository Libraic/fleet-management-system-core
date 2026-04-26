package io.libra.fleet.management.system.core.repository;

import java.util.List;
import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<VehicleEntity, Long> {

    @Query("""
        SELECT v
        FROM VehicleEntity v
        WHERE v.mileage IS NOT NULL
          AND v.lastServiceMileage IS NOT NULL
          AND v.serviceIntervalMileage IS NOT NULL
          AND ABS(v.mileage - v.lastServiceMileage) >= v.serviceIntervalMileage
    """)
    List<VehicleEntity> findVehiclesDueForService();
}
