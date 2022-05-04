package com.example.vinim.appreminder.ui.activity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.vinim.appreminder.R;

import java.util.List;

public class AlarmeAdapter extends ArrayAdapter<Alarme> {

    public AlarmeAdapter(Context context, int resource, List<Alarme> alarmeList){
        super(context, resource, alarmeList);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Alarme alarme1 = getItem(position);
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.oficial_layout_item, parent, false);
        }

        TextView remedioAlarme = (TextView) convertView.findViewById(R.id.layout_item_nomeRemedio);
        TextView horaAlarme = (TextView) convertView.findViewById(R.id.layout_item_horaRemedio);
        TextView offAndon = (TextView) convertView.findViewById(R.id.layout_item_alarmState);
        RelativeLayout layoutOffaNDoN = (RelativeLayout)convertView.findViewById(R.id.layout_item_alarmStateLayout);


        remedioAlarme.setText(alarme1.getRemedio());
        horaAlarme.setText(alarme1.getTime());
        offAndon.setText(alarme1.getState());
        if(alarme1.getState().equals("ON")){
            layoutOffaNDoN.setBackgroundColor(Color.parseColor("#19b8f7"));
        }

        return convertView;
    }
}
