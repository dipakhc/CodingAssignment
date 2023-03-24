package com.sensors.data.metrics.controller;

import com.sensors.data.metrics.dto.request.Sensor;
import com.sensors.data.metrics.dto.response.SensorsData;
import com.sensors.data.metrics.service.SensorsDataService;
import com.sensors.data.metrics.utils.SensorsUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;


@RestController
@RequestMapping(path = "/sensors")
public class SensorDataResource {

    final SensorsDataService sensorsDataService;

    public SensorDataResource(SensorsDataService sensorsDataService) {
        this.sensorsDataService = sensorsDataService;
    }
    @GetMapping(path = "/data", produces = "application/json")
    public SensorsData getSensorData(
            @RequestParam(name = "ids", required = false) String ids,
            @RequestParam(name = "fromDate", required = false) String fromDate,
            @RequestParam(name = "toDate", required = false) String toDate,
            @RequestParam(name = "stats", required = false) String stats,
            @RequestParam(name = "metrics", required = false) String metrics) {

        Sensor sensor = new Sensor();
        SensorsUtils.dateValidation(sensor,fromDate, toDate);
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isBlank(toDate)) {
            // fromDate exists, toDate missing - default to current date
            sensor.setFromLocalDate(SensorsUtils.stringToLocalDateConverter(fromDate));
            sensor.setToLocalDate(LocalDate.now());
        }
        if (StringUtils.isBlank(fromDate) && StringUtils.isBlank(toDate)) {
            //if Both Date is missing then default to current date */
            sensor.setFromLocalDate(LocalDate.now());
            sensor.setToLocalDate(LocalDate.now());
        }
        if (StringUtils.isNotBlank(ids)) {
            sensor.setIds(Arrays.asList(ids.split(",")));
        }
        if (StringUtils.isNotBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            //if Both Date is missing then default to current date */
            sensor.setFromLocalDate(LocalDate.now());
            sensor.setToLocalDate(LocalDate.now());
        }
        if (StringUtils.isNotBlank(stats)) {
            sensor.setStatistic(Arrays.asList(stats.split(",")));
        }
        if (StringUtils.isNotBlank(ids)) {
            sensor.setMetrics(Arrays.asList(metrics.split(",")));
        }
        return sensorsDataService.getSensorsDataByCriteria(sensor).orElse(null);

    }


}
