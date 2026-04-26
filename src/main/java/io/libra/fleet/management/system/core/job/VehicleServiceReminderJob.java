package io.libra.fleet.management.system.core.job;

import java.util.List;
import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import io.libra.fleet.management.system.core.repository.VehicleRepository;
import io.libra.fleet.management.system.core.service.VehicleServiceReminderEmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceReminderJob {

    private final VehicleRepository vehicleRepository;
    private final VehicleServiceReminderEmailService emailService;

//    @Scheduled(cron = "0 0 7 * * *", zone = "Europe/Chisinau")
    @Scheduled(cron = "0 * * * * *", zone = "Europe/Chisinau")
    public void sendDailyServiceReminder() {
        log.info("Starting daily vehicle service reminder job");

        List<VehicleEntity> vehiclesDueForService = vehicleRepository.findVehiclesDueForService();

        if (vehiclesDueForService.isEmpty()) {
            log.info("No vehicles due for service today");
            return;
        }

        emailService.sendVehiclesDueForServiceReport(vehiclesDueForService);

        log.info("Vehicle service reminder sent for {} vehicles", vehiclesDueForService.size());
    }
}
