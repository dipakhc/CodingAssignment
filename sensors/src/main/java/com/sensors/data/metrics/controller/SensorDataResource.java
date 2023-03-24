package com.sensors.data.metrics.controller;

import com.sensors.data.metrics.dto.request.SensorsDataRequest;
import com.sensors.data.metrics.dto.response.SensorsData;
import com.sensors.data.metrics.entities.SensorData;
import com.sensors.data.metrics.service.SensorsDataService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(path = "/sensors/1.0")
public class SensorDataResource {


    final SensorsDataService sensorsDataService;

    public SensorDataResource(SensorsDataService sensorsDataService) {
        this.sensorsDataService = sensorsDataService;
    }

    @GetMapping(path = "/data", produces = "application/json")
    public SensorsData getSensorData(@RequestBody(required = false) SensorsDataRequest sensorsDataRequest, @RequestParam(name = "timeSteps", required = false) String timeSteps, @RequestParam(name = "starTimeStamp", required = false) String starTimeStamp,
                                           @RequestParam(name = "endTimeStamp", required = false) String endTimeStamp) {
        System.out.println("sensorsDataRequest " + sensorsDataRequest);

        return sensorsDataService.getSensorsDataByCriteria(sensorsDataRequest).orElse(null);
    }


}
