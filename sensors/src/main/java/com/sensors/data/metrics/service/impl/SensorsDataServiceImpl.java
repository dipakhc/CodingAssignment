package com.sensors.data.metrics.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sensors.data.metrics.dto.request.SensorsDataRequest;
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

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SensorsDataServiceImpl implements SensorsDataService {

    final NamedParameterJdbcTemplate jdbcTemplate;


    public SensorsDataServiceImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<SensorsData> getSensorsDataByCriteria(SensorsDataRequest sensor) {
        try {

            //TODO: We need to extract the Criteria from SensorsDataRequest and construct the SQL and pass to below function.;
            // return Optional.of(findBySensorsIdS(sensor));


            String sample = "[{\"sensorid\":501,\"sensorname\":\"Home\",\"timeRange\":\"1m\",\"starTimeStamp\":\"date\",\"endTimeStamp\":\"date\",\"temperature\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05},\"humidity\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05}},{\"sensorid\":502,\"sensorname\":\"garage\",\"timeRange\":\"1m\",\"starTimeStamp\":\"date\",\"endTimeStamp\":\"date\",\"temperature\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05},\"humidity\":{\"min\":297.56,\"max\":300.05,\"sum\":300.05,\"avg\":300.05}}]";
            Gson gson = new Gson();
            Type userListType = new TypeToken<ArrayList<SensorDetail>>() {
            }.getType();

            ArrayList<SensorDetail> userArray = gson.fromJson(sample, userListType);
            SensorsData sensorsData = SensorsData.builder().sensorsData(userArray).build();
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

    protected List<SensorDetail> findBySensorsIdS(SensorsDataRequest sensorsDataRequest) {
        List<Integer> integerList = new ArrayList<>();
        sensorsDataRequest.getSensors().stream().forEach(
                sensor1 -> {
                    integerList.add(sensor1.getSensorid());
                }

        );
        SqlParameterSource parameters = new MapSqlParameterSource("ids", integerList);
        //TODO: Construct Dynamic SQL based on Criteria or Create the Views in Database and call the views based on criteria.

        List<SensorDetail> sensorsDataList = jdbcTemplate.query(

                "SELECT time_bucket('15 minutes', time) AS bucket, avg(value) as avgTemp FROM sensor_dataWHERE id IN (:ids)",

                parameters,
                (rs, rowNum) ->
                        SensorDetail.builder()
                                .sensorname(rs.getString("sensorName"))
                                .sensorid(rs.getInt("id"))
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
