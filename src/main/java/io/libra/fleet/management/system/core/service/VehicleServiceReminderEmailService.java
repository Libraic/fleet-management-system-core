package io.libra.fleet.management.system.core.service;

import java.util.List;
import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleServiceReminderEmailService {

    private static final String EMAIL = "victor.nani.cs@gmail.com";
    private static final String EMAIL_SUBJECT = "Memento pentru mentenanța vehiculului";

    private final JavaMailSender mailSender;

    public void sendVehiclesDueForServiceReport(List<VehicleEntity> vehicles) {
        if (vehicles == null || vehicles.isEmpty()) {
            return;
        }

        String body = buildEmailBody(vehicles);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(EMAIL);
        message.setSubject(EMAIL_SUBJECT);
        message.setText(body);

        mailSender.send(message);
    }

    private String buildEmailBody(List<VehicleEntity> vehicles) {
        StringBuilder sb = new StringBuilder();

        sb.append("Bună dimineața,\n\n");
        sb.append("Următoarele vehicule au parcursul trecut de valoarea de prag și necesită servicii de mentenanță:\n\n");

        for (VehicleEntity vehicle : vehicles) {
            sb.append("- ")
                .append(vehicle.getMake()).append(" ")
                .append(vehicle.getModel())
                .append(" | Numărul de înmatriculare: ").append(vehicle.getRegistrationNumber())
                .append(" | Ultima revizie: ").append(vehicle.getLastServiceMileage()).append(" km")
                .append(" | Parcursul curent: ").append(vehicle.getMileage()).append(" km")
                .append("\n");
        }

        sb.append("\nVă rugăm să asigurați serviciile de mentenanță corespunzătoare.\n\n");
        sb.append("Cu drag,\n");
        sb.append("Sistemul de gestionare a flotei Agrostoc");

        return sb.toString();
    }
}
