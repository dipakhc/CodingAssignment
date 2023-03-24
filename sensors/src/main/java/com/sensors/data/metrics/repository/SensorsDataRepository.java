package com.sensors.data.metrics.repository;


import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Date;
import java.util.List;

@NoRepositoryBean
public interface SensorsDataRepository<T, ID> extends JpaRepository<T, ID> {
    default List<T> getAll() {
        return findAll();
    }


    default Specification<T> getSensorIDSpec(Integer sensorID) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("sensorID"), sensorID);
    }

    default Specification<T> getSensorIDSpec(List<Integer> sensorIDS) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.in(root.get("sensorID")).value(sensorIDS);

    }

    default Specification<T> getSensorIdsSpec(List<Integer> sensorIds) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.in(root.get("sensorID")).value(sensorIds);
    }

    default Specification<T> getTemperatureSpec(Integer temperature) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("temperature"), temperature);
    }

    default Specification<T> getHumiditySpec(Integer humidity) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("humidity"), humidity);
    }

    default Specification<T> getByDateRangeSpec(Date fromDate, Date toDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.get("timeStamp"), fromDate, toDate);
    }

    default Specification<T> getByDateSpec(Date fromDate) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("timeStamp"), fromDate);
    }
}


