package com.example.asteroid.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asteroid.R;
import com.example.asteroid.model.WindData;
import com.example.asteroid.view.WindRoseView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    TextView solNumberDetail, temperatureDetail, pressureDetail;
    WindRoseView windRoseView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        solNumberDetail = findViewById(R.id.solNumberDetail);
        temperatureDetail = findViewById(R.id.temperatureDetail);
        pressureDetail = findViewById(R.id.pressureDetail);
        windRoseView = findViewById(R.id.windRoseView);

        String solNumber = getIntent().getStringExtra("solNumber");
        double avgTemp = getIntent().getDoubleExtra("avgTemp", 0);
        double minTemp = getIntent().getDoubleExtra("minTemp", 0);
        double maxTemp = getIntent().getDoubleExtra("maxTemp", 0);
        double avgPressure = getIntent().getDoubleExtra("avgPressure", 0);
        double minPressure = getIntent().getDoubleExtra("minPressure", 0);
        double maxPressure = getIntent().getDoubleExtra("maxPressure", 0);
        ArrayList<WindData> windDataList = getIntent().getParcelableArrayListExtra("windData");

        // ✅ Mostrar datos
        solNumberDetail.setText("Sol nº" + solNumber);
        temperatureDetail.setText("Température : avg: " + avgTemp + " min: " + minTemp + " max: " + maxTemp);
        pressureDetail.setText("Pression : avg: " + avgPressure + " min: " + minPressure + " max: " + maxPressure);

        if (windDataList != null) {
            windRoseView.setWindDataList(windDataList);
        }
    }
}
