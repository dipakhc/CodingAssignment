package com.sensors.data.metrics.dao;

import com.sensors.data.metrics.entities.SensorData;
import com.sensors.data.metrics.repository.SensorsDataRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface SensorDataDao extends SensorsDataRepository<SensorData, Integer>, JpaSpecificationExecutor<SensorData> {

    // Optional<List<SensorsData>> streamAllBySensorIdInAndTimeBetweenOrderBySensorId(List<Integer> SensorIds, Date fromDate,Date toDate);
    default Optional<List<SensorData>> getSensorDataByCriteria(List<Integer> sensorIds, Date fromDate, Date toDate, String temp, String humidity) {
        Specification specification = null;
        if (!CollectionUtils.isEmpty(sensorIds)) {
            specification = getSensorIdsSpec(sensorIds);
        }
        if (fromDate != null && toDate != null) {
            if (specification != null) {
                specification = specification.and(getByDateRangeSpec(fromDate, toDate));
            } else {
                specification = getByDateRangeSpec(fromDate, toDate);
            }

        }
        if (fromDate != null && toDate == null) {
            if (specification != null) {
                specification = specification.and(getByDateSpec(fromDate));
            } else {
                specification = getByDateRangeSpec(fromDate, toDate);
            }
        }

        if (specification != null) {
            return Optional.of(findAll(specification));
        }
        return Optional.of(findAll());


    }

}
