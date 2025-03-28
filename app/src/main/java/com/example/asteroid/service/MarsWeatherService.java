package com.example.asteroid.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asteroid.model.MarsSol;
import com.example.asteroid.model.WindData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MarsWeatherService {

    public interface MarsWeatherCallback {
        void onSuccess(List<MarsSol> solList);
        void onError(Exception e);
    }

    public void fetchMarsWeather(Context context, String apiKey, MarsWeatherCallback callback) {
        String url = "https://api.nasa.gov/insight_weather/?api_key=" + apiKey + "&feedtype=json&ver=1.0";
        RequestQueue queue = Volley.newRequestQueue(context);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                response -> {
                    try {
                        List<MarsSol> solList = new ArrayList<>();
                        JSONObject jsonResponse = new JSONObject(response);
                        JSONArray solKeys = jsonResponse.getJSONArray("sol_keys");

                        for (int i = 0; i < solKeys.length(); i++) {
                            String sol = solKeys.getString(i);
                            JSONObject solData = jsonResponse.getJSONObject(sol);

                            JSONObject temp = solData.getJSONObject("AT");
                            double avgTemp = temp.optDouble("av", 0);
                            double minTemp = temp.optDouble("mn", 0);
                            double maxTemp = temp.optDouble("mx", 0);

                            JSONObject pressure = solData.getJSONObject("PRE");
                            double avgPressure = pressure.optDouble("av", 0);
                            double minPressure = pressure.optDouble("mn", 0);
                            double maxPressure = pressure.optDouble("mx", 0);

                            String season = solData.optString("Season", "unknown");

                            MarsSol marsSol = new MarsSol(
                                    sol, avgTemp, minTemp, maxTemp,
                                    avgPressure, minPressure, maxPressure,
                                    season
                            );

                            List<WindData> windDataList = new ArrayList<>();
                            if (solData.has("WD")) {
                                JSONObject wdObject = solData.getJSONObject("WD");

                                Iterator<String> keys = wdObject.keys();
                                while (keys.hasNext()) {
                                    String key = keys.next();
                                    if (!key.equals("most_common")) {
                                        JSONObject windDir = wdObject.optJSONObject(key);
                                        if (windDir == null) continue;

                                        float degrees = (float) windDir.optDouble("compass_degrees", 0);
                                        int count = windDir.optInt("ct", 0);

                                        windDataList.add(new WindData(degrees, count));
                                    }
                                }

                                marsSol.setWindDataList(windDataList);
                            }

                            solList.add(marsSol);
                        }

                        callback.onSuccess(solList);

                    } catch (JSONException e) {
                        callback.onError(e);
                    }
                },
                error -> callback.onError(error)
        );

        queue.add(stringRequest);
    }
}
