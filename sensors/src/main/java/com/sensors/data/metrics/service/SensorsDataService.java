package com.sensors.data.metrics.service;

import com.sensors.data.metrics.dto.request.SensorsDataRequest;
import com.sensors.data.metrics.dto.response.SensorsData;
import com.sensors.data.metrics.entities.SensorData;

import java.util.List;
import java.util.Optional;


public interface SensorsDataService {
    public Optional<SensorsData> getSensorsDataByCriteria(SensorsDataRequest sensor);
}
