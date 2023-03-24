package com.sensors.data.metrics.service;

import com.sensors.data.metrics.dto.request.Sensor;
import com.sensors.data.metrics.dto.response.SensorsData;

import java.util.Optional;


public interface SensorsDataService {
    public Optional<SensorsData> getSensorsDataByCriteria(Sensor sensor);
}
