package com.fundingcircle.util;

import com.fundingcircle.data.TimeSeriesObservation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

@Service
public class JsonUtil {
    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    public Collection<TimeSeriesObservation> buildTimeSeriesObservationList(String json) {
        Collection<TimeSeriesObservation> observations = new Vector<>();
        JSONObject obj = new JSONObject(json);

        JSONArray arr = obj.getJSONArray("observations");
        arr.forEach(item -> {
            try {
                TimeSeriesObservation element = build((JSONObject) item);
                observations.add(element);
            } catch (Exception exception) {
                logger.error(exception.getMessage(), exception);
            }
        });

        return observations;
    }

    private TimeSeriesObservation build(JSONObject json) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date obsDate = sdf.parse(json.getString("date"));
        return new TimeSeriesObservation()
                .setDate(obsDate)
                .setValue(json.getString("value").equals(".") ? null : Double.parseDouble(json.getString("value")));
}
}
