package com.example.asteroid.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.asteroid.R;
import com.example.asteroid.adapter.MarsSolAdapter;
import com.example.asteroid.model.MarsSol;
import com.example.asteroid.service.MarsWeatherService;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private TextView solCount;
    private ListView listView;
    private List<MarsSol> marsSolList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        solCount = findViewById(R.id.solCount);
        listView = findViewById(R.id.listView);

        String apiKey = "sFgXWnjs1blKetFfycX1CmzPxfAIIRKhSeNgarVe"; // reemplaza con tu clave

        MarsWeatherService service = new MarsWeatherService();
        service.fetchMarsWeather(this, apiKey, new MarsWeatherService.MarsWeatherCallback() {
            @Override
            public void onSuccess(List<MarsSol> solList) {
                marsSolList = solList;
                solCount.setText("NB SOLS : " + solList.size());

                MarsSolAdapter adapter = new MarsSolAdapter(HomeActivity.this, solList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onError(Exception e) {
                solCount.setText("Erreur de chargement des données");
                Toast.makeText(HomeActivity.this, "Erreur : " + e.getMessage(), Toast.LENGTH_LONG).show();
                e.printStackTrace();
            }
        });

        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            MarsSol selectedSol = marsSolList.get(position);

            Intent intent = new Intent(HomeActivity.this, DetailActivity.class);
            intent.putExtra("solNumber", selectedSol.getSolNumber());
            intent.putExtra("avgTemp", selectedSol.getAverageTemp());
            intent.putExtra("minTemp", selectedSol.getMinTemp());
            intent.putExtra("maxTemp", selectedSol.getMaxTemp());
            intent.putExtra("avgPressure", selectedSol.getAveragePressure());
            intent.putExtra("minPressure", selectedSol.getMinPressure());
            intent.putExtra("maxPressure", selectedSol.getMaxPressure());
            intent.putParcelableArrayListExtra("windData",
                    new ArrayList<>(selectedSol.getWindDataList()));

            startActivity(intent);
        });
    }
}
