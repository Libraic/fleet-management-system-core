package io.libra.fleet.management.system.core.model.response;

import lombok.Builder;

@Builder
public record GetVehicleResponse(
    String id,
    String make,
    String model,
    int mileage,
    String registrationNumber,
    int lastServiceMileage,
    int serviceIntervalMileage
) {
}
