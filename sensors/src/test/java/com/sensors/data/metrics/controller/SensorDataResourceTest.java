package com.sensors.data.metrics.controller;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.Optional;

import com.google.gson.Gson;
import com.sensors.data.metrics.dto.response.SensorsData;
import com.sensors.data.metrics.exception.SensorDataBusinessException;
import com.sensors.data.metrics.service.impl.SensorsDataServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SensorDataResourceTest {

    @Mock
    protected SensorsDataServiceImpl sensorsDataService;

    @InjectMocks
    protected SensorDataResource sensorDataResource;

    @Test(expected = SensorDataBusinessException.class)
    public void getTransactionsFutureDateTest() {
        sensorDataResource.getSensorData("S1,S2", "1W","2023-03-16", "2023-03-31", "AVG,MIN,MAX", "WND,TMP");
    }

    @Test()
    public void getTransactionsTest() {

        String sample = "{\"starTimeStamp\":\"date\",\"endTimeStamp\":\"date\",\"sensors\":[{\"id\":\"S1\",\"name\":\"Home\",\"temperature\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05},\"humidity\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05}},{\"id\":\"S2\",\"name\":\"garage\",\"temperature\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05},\"humidity\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05}}]}";
        Gson gson = new Gson();
        SensorsData sensorsData = gson.fromJson(sample, SensorsData.class);
        when(sensorsDataService.getSensorsDataByCriteria(any())).thenReturn(Optional.of(sensorsData));
        SensorsData sensorData =  sensorDataResource.getSensorData("S1,S2", "1W","2023-03-16", "2023-03-17", "AVG,MIN,MAX", "WND,TMP");
        assertNotNull(sensorData);

    }

}