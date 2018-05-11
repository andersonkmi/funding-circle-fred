package com.fundingcircle.util;

import com.fundingcircle.data.TimeSeriesObservation;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Vector;

@Service
public class JsonUtil {

    public Collection<TimeSeriesObservation> buildTimeSeriesObservationList(String json) {
        Collection<TimeSeriesObservation> observations = new Vector<>();
        JSONObject obj = new JSONObject(json);

        JSONArray arr = obj.getJSONArray("observations");
        arr.forEach(item -> observations.add(build((JSONObject) item)));

        return observations;
    }

    private TimeSeriesObservation build(JSONObject json) {
        return new TimeSeriesObservation()
                .setDate(json.getString("date"))
                .setValue(json.getString("value").equals(".") ? null : Double.parseDouble(json.getString("value")));
    }
}
