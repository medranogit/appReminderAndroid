package com.example.vinim.appreminder.dao;

import com.example.vinim.appreminder.ui.activity.Alarme;

import java.util.ArrayList;
import java.util.List;

public class AlarmeDAO {
    //List<Alarme> dizendo que sera uma lista de classes.
    private final static List<Alarme> alarmesList = new ArrayList<>();

    public void salva(Alarme alarme) {
        alarmesList.add(alarme);
    }

    public List<Alarme> todosAlarmes(){
        return new ArrayList<>(alarmesList);
    }
}
