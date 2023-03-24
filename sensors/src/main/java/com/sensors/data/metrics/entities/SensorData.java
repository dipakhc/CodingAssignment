package com.sensors.data.metrics.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;


@Getter
@Setter
@Accessors(chain = true)
@ToString
@Builder
public class SensorData {

    private int sensorId;

    private String sensorName;

    private java.sql.Timestamp timeStamp;

    private Integer temperature;

    private Integer humidity;

    private int min;
    private int max;
    private int avg;
    private int sum;
}


