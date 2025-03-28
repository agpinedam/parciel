package com.example.asteroid.service;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.asteroid.model.MarsSol;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
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
                            double avgTemp = temp.getDouble("av");
                            double minTemp = temp.getDouble("mn");
                            double maxTemp = temp.getDouble("mx");

                            JSONObject pressure = solData.getJSONObject("PRE");
                            double avgPressure = pressure.getDouble("av");
                            double minPressure = pressure.getDouble("mn");
                            double maxPressure = pressure.getDouble("mx");

                            String season = solData.getString("Season");

                            MarsSol marsSol = new MarsSol(
                                    sol, avgTemp, minTemp, maxTemp,
                                    avgPressure, minPressure, maxPressure,
                                    season);
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