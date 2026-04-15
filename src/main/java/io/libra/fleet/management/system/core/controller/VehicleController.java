package io.libra.fleet.management.system.core.controller;

import io.libra.fleet.management.system.core.facade.VehicleFacade;
import io.libra.fleet.management.system.core.model.request.UpsertVehicleRequest;
import io.libra.fleet.management.system.core.model.response.GetVehicleResponse;
import io.libra.fleet.management.system.core.model.response.UpsertVehicleResponse;
import io.libra.fleet.management.system.core.model.response.common.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/vehicles")
@Slf4j
public class VehicleController {

    private final VehicleFacade vehicleFacade;

    @PostMapping
    public ResponseEntity<UpsertVehicleResponse> upsertVehicle(@RequestBody UpsertVehicleRequest request) {
        log.info("A request to upsert a vehicle has been received.");
        UpsertVehicleResponse response = vehicleFacade.upsertVehicle(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<PageResponse<GetVehicleResponse>> getVehicles(
        @RequestParam(defaultValue = "0") int page
    ) {
        PageResponse<GetVehicleResponse> response = vehicleFacade.getVehicles(page);
        return ResponseEntity.ok(response);
    }
}
