package com.sensors.data.metrics.service.impl;

import com.google.gson.Gson;
import com.sensors.data.metrics.dto.request.Sensor;
import com.sensors.data.metrics.dto.response.Humidity;
import com.sensors.data.metrics.dto.response.SensorDetail;
import com.sensors.data.metrics.dto.response.SensorsData;
import com.sensors.data.metrics.dto.response.Temperature;
import com.sensors.data.metrics.exception.SensorDataBusinessException;
import com.sensors.data.metrics.service.SensorsDataService;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorsDataServiceImpl implements SensorsDataService {

    final NamedParameterJdbcTemplate jdbcTemplate;
    public SensorsDataServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Optional<SensorsData> getSensorsDataByCriteria(Sensor sensor) {
        try {
            //TODO: We need to extract the Criteria from SensorsDataRequest and construct the SQL and pass to below function.;
            // return Optional.of(findBySensorsIdS(sensor));
            String sample = "{\"starTimeStamp\":\"date\",\"endTimeStamp\":\"date\",\"sensors\":[{\"id\":\"S1\",\"name\":\"Home\",\"temperature\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05},\"humidity\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05}},{\"id\":\"S2\",\"name\":\"garage\",\"temperature\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05},\"humidity\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05}}]}";
            Gson gson = new Gson();
            SensorsData sensorsData = gson.fromJson(sample, SensorsData.class);
            if (sensorsData == null) {
                throw new SensorDataBusinessException("No Sensor Data found");
            }
            return Optional.of(sensorsData);
        } catch (SensorDataBusinessException e) {
            throw new SensorDataBusinessException(e.getMessage());
        } catch (Exception e) {
            throw new SensorDataBusinessException(e.getLocalizedMessage(), e);
        }
    }
    protected List<SensorDetail> findBySensorsIdS(Sensor sensorsDataRequest) {
        SqlParameterSource parameters = new MapSqlParameterSource("ids", sensorsDataRequest.getIds());
        //TODO: Construct Dynamic SQL based on Criteria or Create the Views in Database and call the views based on criteria.
        List<SensorDetail> sensorsDataList = jdbcTemplate.query(
                "SELECT time_bucket('15 minutes', time) AS bucket, avg(value) as avgTemp FROM sensor_dataWHERE id IN (:ids)",
                parameters,
                (rs, rowNum) ->
                        SensorDetail.builder()
                                .name(rs.getString("name"))
                                .id(rs.getString("id"))
                                .temperature(Temperature.builder()
                                        .avg(rs.getDouble("avgTemp"))
                                        .min(rs.getDouble("minTemp"))
                                        .sum(rs.getDouble("sumTemp"))
                                        .max(rs.getDouble("maxTemp"))
                                        .build())
                                .humidity(Humidity.builder()
                                        .avg(rs.getDouble("avgHumidity"))
                                        .min(rs.getDouble("minHumidity"))
                                        .sum(rs.getDouble("sumHumidity"))
                                        .max(rs.getDouble("maxHumidity"))
                                        .build())
                                .build()

        );
        return sensorsDataList;
    }
}
