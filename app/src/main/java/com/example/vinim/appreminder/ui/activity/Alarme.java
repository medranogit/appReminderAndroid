package com.example.vinim.appreminder.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;


public class Alarme {

    private final String remedio;
    private final String descricao;
    private final Integer hora;
    private final Integer minuto;
    private final Boolean vibrar;

    public Alarme(String remedio, String descricao, Integer hora, Integer minuto, Boolean vibrar) {
        this.remedio = remedio;
        this.descricao = descricao;
        this.hora = hora;
        this.minuto = minuto;
        this.vibrar = vibrar;
    }

    @NonNull
    @Override //Esse é o texto que aparecera na lista. No caso oq representa a classe.
    public String toString() {
        String hora = conversaoHoraParaTexto();
        String textLista = hora + " - " + remedio;
        return textLista;
    }

    //ACESSORES
    @NonNull
    public String getRemedio() {
        return remedio;
    }

    @NonNull
    public String getDescricao() {
        return descricao;
    }

    @NonNull
    public String getHour() {
        String timeString = conversaoHoraParaTexto();
        return timeString;
    }

    @NonNull
    public String getVibrar() {
        String vibrarString = Boolean.toString(vibrar);
        return vibrarString;
    }

    //METODOS
    private String conversaoHoraParaTexto(){
        String stringHour = Integer.toString(hora);
        String stringMinute = Integer.toString(minuto);
        String timeString =  stringHour + ":" + stringMinute;
        return timeString;
    }
}
