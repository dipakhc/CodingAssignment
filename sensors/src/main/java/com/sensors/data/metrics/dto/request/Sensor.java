package com.sensors.data.metrics.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.List;


@JsonIgnoreProperties
@Getter
@Setter
@SuperBuilder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
public class Sensor {

    @JsonProperty("sensorid")
    private Integer sensorid;
    @JsonProperty("sensorname")
    private String sensorname;
    @JsonProperty("metrics")
    private List<String> metrics;
    @JsonProperty("statistic")
    private List<String> statistic;


}