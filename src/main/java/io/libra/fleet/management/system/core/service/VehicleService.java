package io.libra.fleet.management.system.core.service;

import java.util.ArrayList;
import java.util.List;
import io.libra.fleet.management.system.core.model.entity.VehicleEntity;
import io.libra.fleet.management.system.core.repository.VehicleRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final EntityManager entityManager;

    public void persistVehicle(VehicleEntity vehicleEntity) {
        log.debug("Persisting the Vehicle with UUID=[{}].", vehicleEntity.getUuid());
        vehicleRepository.save(vehicleEntity);
    }

    public List<VehicleEntity> getAllVehicles(int page) {
        log.info("Retrieving vehicles by criteria.");
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VehicleEntity> cq = cb.createQuery(VehicleEntity.class);

        Root<VehicleEntity> root = cq.from(VehicleEntity.class);

        List<Predicate> predicates = new ArrayList<>();

        cq.select(root)
            .where(cb.and(predicates.toArray(new Predicate[0])))
            .orderBy(cb.desc(root.get("createdAt")));

        TypedQuery<VehicleEntity> query = entityManager.createQuery(cq);

        int pageSize = 15;
        query.setFirstResult(page * pageSize);
        query.setMaxResults(pageSize);

        return query.getResultList();
    }

    public long countVehicles() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<VehicleEntity> root = cq.from(VehicleEntity.class);

        cq.select(cb.count(root));

        return entityManager.createQuery(cq).getSingleResult();
    }
}
