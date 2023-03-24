package com.sensors.data.metrics.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "sensorData"
})
@Getter
@Setter
@Accessors(chain = true)
@ToString
@Builder
public class SensorsData {
    @JsonProperty("starTimeStamp")
    public String starTimeStamp;
    @JsonProperty("endTimeStamp")
    public String endTimeStamp;
    @JsonProperty("sensorsData")
    public List<SensorDetail> sensors;

}