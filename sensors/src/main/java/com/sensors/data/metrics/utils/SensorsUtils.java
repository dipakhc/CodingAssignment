package com.sensors.data.metrics.utils;


import com.sensors.data.metrics.dto.request.Sensor;
import com.sensors.data.metrics.exception.SensorDataBusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import static com.sensors.data.metrics.utils.SensorsConstant.*;

@Component
public class SensorsUtils {

    public static LocalDate stringToLocalDateConverter(String dateInString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(YYYY_MM_DD);
        return LocalDate.parse(dateInString, formatter);
    }

    public static void dateValidation(Sensor sensor , String fromDate, String toDate) {
        if ((StringUtils.isNotBlank(fromDate) && !matches(fromDate)) || StringUtils.isNotBlank(toDate) && !matches(toDate)) {
            throw new SensorDataBusinessException((ENTER_DATE_MESSAGE));
        }
        if (StringUtils.isBlank(fromDate) && StringUtils.isNotBlank(toDate)) {
            throw new SensorDataBusinessException((FROM_DATE_MISSING));
        }

        if (StringUtils.isNotBlank(fromDate) && StringUtils.isBlank(toDate)) {
            // fromDate exists, toDate missing - default to current date
            sensor.setToLocalDate(LocalDate.now());
        }
        if(StringUtils.isNotBlank(toDate)){
            sensor.setToLocalDate(SensorsUtils.stringToLocalDateConverter(toDate));
        }
        if(StringUtils.isNotBlank(fromDate)){
            sensor.setFromLocalDate(SensorsUtils.stringToLocalDateConverter(fromDate));
        }
        if (StringUtils.isBlank(fromDate) && StringUtils.isBlank(toDate)) {
            //if Both Date is missing then default to current date */
            sensor.setFromLocalDate(LocalDate.now());
            sensor.setToLocalDate(LocalDate.now());
        }
        if(sensor.getToLocalDate().isAfter(LocalDate.now()) ||sensor.getFromLocalDate().isAfter(LocalDate.now())){
            throw new SensorDataBusinessException(("Future Date is not allowed"));
        }
    }

    public static Date stringToDateConverter(String dateInString) {
        SimpleDateFormat formatter = new SimpleDateFormat(YYYY_MM_DD, Locale.ENGLISH);
        try {
            return formatter.parse(dateInString);
        } catch (ParseException e) {
            throw new SensorDataBusinessException(e.getLocalizedMessage(), e);
        }
    }

    public static Date convertToDateViaInstant(LocalDate dateToConvert) {
        return java.util.Date.from(dateToConvert.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
    }

    public static boolean matches(String date) {
        return DATE_PATTERN.matcher(date).matches();
    }


}
