package io.libra.fleet.management.system.core.model.request;

public record UpsertVehicleRequest(
    String id,
    String make,
    String model,
    String registrationNumber,
    Integer mileage
) {
}
