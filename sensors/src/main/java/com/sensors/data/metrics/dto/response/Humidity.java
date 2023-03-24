package com.sensors.data.metrics.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "min",
        "max",
        "sum",
        "avg"
})
@Getter
@Setter
@Accessors(chain = true)
@ToString
@Builder
public class Humidity {

    @JsonProperty("min")
    public Double min;
    @JsonProperty("max")
    public Double max;
    @JsonProperty("sum")
    public Double sum;
    @JsonProperty("avg")
    public Double avg;

}