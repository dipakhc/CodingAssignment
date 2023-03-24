package com.sensors.data.metrics.utils;

import java.util.regex.Pattern;

public class SensorsConstant {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String ENTER_DATE_MESSAGE =
            "Please enter valid date format " + YYYY_MM_DD + " for enterDate field.";
    public static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    public static final String FROM_DATE_MISSING =
            "fromDate missing as toDate is exist";


}
