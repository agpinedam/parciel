package com.example.asteroid.activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asteroid.R;
import com.example.asteroid.adapter.MarsWeatherAdapter;
import com.example.asteroid.model.MarsSol;
import com.example.asteroid.service.MarsWeatherService;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView solCount;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        solCount = findViewById(R.id.solCount); // Cambiar ID en XML a "solCount" por claridad
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MarsWeatherService service = new MarsWeatherService();
        String apiKey = "sFgXWnjs1blKetFfycX1CmzPxfAIIRKhSeNgarVe"; // Reemplazar por tu propia API Key

        service.fetchMarsWeather(this, apiKey, new MarsWeatherService.MarsWeatherCallback() {
            @Override
            public void onSuccess(List<MarsSol> solList) {
                solCount.setText("NB SOLS : " + solList.size());
                recyclerView.setAdapter(new MarsWeatherAdapter(solList));
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                solCount.setText("Erreur de chargement des données");
            }
        });
    }
}

