package com.example.asteroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.asteroid.R;

public class DetailActivity extends AppCompatActivity {

    TextView solNumberDetail, temperatureDetail, pressureDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        solNumberDetail = findViewById(R.id.solNumberDetail);
        temperatureDetail = findViewById(R.id.temperatureDetail);
        pressureDetail = findViewById(R.id.pressureDetail);

        // Obtener los datos del Intent
        Intent intent = getIntent();
        String solNumber = intent.getStringExtra("solNumber");
        double avgTemp = intent.getDoubleExtra("avgTemp", 0);
        double minTemp = intent.getDoubleExtra("minTemp", 0);
        double maxTemp = intent.getDoubleExtra("maxTemp", 0);
        double avgPressure = intent.getDoubleExtra("avgPressure", 0);
        double minPressure = intent.getDoubleExtra("minPressure", 0);
        double maxPressure = intent.getDoubleExtra("maxPressure", 0);

        // Configurar los textos
        solNumberDetail.setText("Sol nº" + solNumber);
        temperatureDetail.setText(String.format("Température : avg: %.2f min: %.2f max: %.2f",
                avgTemp, minTemp, maxTemp));
        pressureDetail.setText(String.format("Pression : avg: %.2f min: %.2f max: %.2f",
                avgPressure, minPressure, maxPressure));
    }
}
