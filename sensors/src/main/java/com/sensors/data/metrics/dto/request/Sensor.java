package com.sensors.data.metrics.dto.request;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@SuperBuilder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Sensor {

    private  String timeSteps;
    LocalDate toLocalDate;
    LocalDate fromLocalDate;
    private List<String> ids;
    private List<String> metrics;
    private List<String> statistic;


}