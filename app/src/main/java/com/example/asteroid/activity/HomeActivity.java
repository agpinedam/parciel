package com.example.asteroid.activity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asteroid.R;
import com.example.asteroid.adapter.MarsSolAdapter;
import com.example.asteroid.model.MarsSol;
import com.example.asteroid.service.MarsWeatherService;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    TextView solCount;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        solCount = findViewById(R.id.solCount);
        listView = findViewById(R.id.listView);

        MarsWeatherService service = new MarsWeatherService();
        String apiKey = "sFgXWnjs1blKetFfycX1CmzPxfAIIRKhSeNgarVe";

        service.fetchMarsWeather(this, apiKey, new MarsWeatherService.MarsWeatherCallback() {
            @Override
            public void onSuccess(List<MarsSol> solList) {
                solCount.setText("NB SOLS : " + solList.size());
                MarsSolAdapter adapter = new MarsSolAdapter(HomeActivity.this, solList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(Exception e) {
                e.printStackTrace();
                solCount.setText("Erreur de chargement des données");
            }
        });
    }
}