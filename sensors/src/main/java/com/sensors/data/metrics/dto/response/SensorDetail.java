package com.sensors.data.metrics.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Accessors(chain = true)
@ToString
@Builder
public class SensorDetail {

    @JsonProperty("sensorid")
    public Integer sensorid;
    @JsonProperty("sensorname")
    public String sensorname;
    @JsonProperty("timeRange")
    public String timeRange;
    @JsonProperty("starTimeStamp")
    public String starTimeStamp;
    @JsonProperty("endTimeStamp")
    public String endTimeStamp;
    @JsonProperty("temperature")
    public Temperature temperature;
    @JsonProperty("humidity")
    public Humidity humidity;

}