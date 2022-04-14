package com.example.vinim.appreminder.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;


public class Alarme {

    private final String remedio;
    private final String descricao;
    private final Boolean domingo, segunda, terca, quarta, quinta, sexta, sabado;
    private final Integer hora;
    private final Integer minuto;
    private final Boolean vibrar;

    public Alarme(String remedio, String descricao, Integer hora, Integer minuto, Boolean vibrar,
                  Boolean domingo, Boolean segunda, Boolean terça, Boolean quarta, Boolean quinta, Boolean sexta, Boolean sabado) {
        this.remedio = remedio;
        this.descricao = descricao;
        this.hora = hora;
        this.minuto = minuto;
        this.vibrar = vibrar;
        this.domingo = domingo;
        this.segunda = segunda;
        this.terca = terça;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
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
    public String getDias() {
        String domingoString = Boolean.toString(domingo);
        String segundaString = Boolean.toString(segunda);
        String terçaString = Boolean.toString(terca);
        String quartaString = Boolean.toString(quarta);
        String quintaString = Boolean.toString(quinta);
        String sextaString = Boolean.toString(sexta);
        String sabadoString = Boolean.toString(sabado);
        String text = "dom: " + domingoString + " seg: " + segundaString + " ter: " + terçaString +
                " quart: " + quartaString + " quin: " + quintaString + " sex: " + sextaString +
                " sab: " + sabadoString;
        return text;
    }

    //METODOS
    private String conversaoHoraParaTexto(){
        String stringHour = Integer.toString(hora);
        String stringMinute = Integer.toString(minuto);
        String timeString =  stringHour + ":" + stringMinute;
        return timeString;
    }

    //Ativar

    //Desativa

    //


}
