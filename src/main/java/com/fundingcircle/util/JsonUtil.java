package com.fundingcircle.util;

import com.fundingcircle.data.TimeSeriesObservation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

import static java.util.Calendar.*;

@Service
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public Collection<TimeSeriesObservation> buildTimeSeriesObservationList(String json) {
        Collection<TimeSeriesObservation> observations = new Vector<>();
        JSONObject obj = new JSONObject(json);

        JSONArray arr = obj.getJSONArray("observations");
        arr.forEach(item -> {
            TimeSeriesObservation element = build((JSONObject) item);
            observations.add(element);
        });

        return observations;
    }

    private TimeSeriesObservation build(JSONObject json) {
        String[] splitted = json.getString("date").split("-");
        Calendar observationDate = Calendar.getInstance();
        observationDate.set(YEAR, Integer.parseInt(splitted[0]));
        observationDate.set(MONTH, Integer.parseInt(splitted[1]) - 1);
        observationDate.set(DAY_OF_MONTH, Integer.parseInt(splitted[2]));
        observationDate.set(HOUR, 0);
        observationDate.set(MINUTE, 0);
        observationDate.set(SECOND, 0);
        observationDate.set(MILLISECOND, 0);
        return new TimeSeriesObservation()
                .setDate(observationDate)
                .setValue(json.getString("value").equals(".") ? null : Double.parseDouble(json.getString("value")));
}
}
