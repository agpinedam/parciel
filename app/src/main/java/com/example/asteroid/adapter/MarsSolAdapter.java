package com.example.asteroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.asteroid.R;
import com.example.asteroid.model.MarsSol;
import java.util.List;

public class MarsSolAdapter extends ArrayAdapter<MarsSol> {

    public MarsSolAdapter(Context context, List<MarsSol> sols) {
        super(context, 0, sols);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MarsSol sol = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_sol, parent, false);
        }

        TextView solNumber = convertView.findViewById(R.id.solNumber);
        TextView temperature = convertView.findViewById(R.id.temperature);
        TextView pressure = convertView.findViewById(R.id.pressure);

        solNumber.setText("Sol nº" + sol.getSolNumber());
        temperature.setText("Température :" + sol.getAverageTemp());
        pressure.setText("Pression :" + sol.getAveragePressure());

        return convertView;
    }
}
