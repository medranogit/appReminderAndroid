package com.example.vinim.appreminder.ui.activity;

import android.content.Intent;
import android.support.annotation.NonNull;


public class Alarme {

    private String id;
    private String remedio;
    private Boolean vibrar;
    private Boolean domingo, segunda, terca, quarta, quinta, sexta, sabado;
    private String hora, minuto;
    private String state;
    private String descricao;

    public Alarme(String id, String horas, String minutos, String remedio, String descricao, Boolean vibrar,
                  Boolean domingo, Boolean segunda, Boolean terca, Boolean quarta,
                  Boolean quinta, Boolean sexta, Boolean sabado, String state) {

        this.id = id;
        this.remedio = remedio;
        this.descricao = descricao;
        this.vibrar = vibrar;
        this.hora = horas;
        this.minuto = minutos;
        this.domingo = domingo;
        this.segunda = segunda;
        this.terca = terca;
        this.quarta = quarta;
        this.quinta = quinta;
        this.sexta = sexta;
        this.sabado = sabado;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRemedio() {
        return remedio;
    }

    public void setRemedio(String remedio) {
        this.remedio = remedio;
    }

    public Boolean getVibrar() {
        return vibrar;
    }

    public void setVibrar(Boolean vibrar) {
        this.vibrar = vibrar;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTime() {
        String time = hora +":"+minuto;
        return time;
    }

    public void setTime(String time) {
        String[] timeSplited = time.split(":");
        this.hora = timeSplited[0];
        this.minuto = timeSplited[1];
    }

    public void setDiasSemana(Boolean dom, Boolean seg, Boolean ter, Boolean qua,
                              Boolean qui, Boolean sext, Boolean sab){
        this.domingo = dom;
        this.segunda = seg;
        this.terca = ter;
        this.quarta = qua;
        this.quinta = qui;
        this.sexta = sext;
        this.sabado = sab;
    }

    public String getDiasSemana(){

        //retornar o texto dias da semana baseada em uma estrutura condicional
        if(domingo == true && segunda == true && terca == true && quarta == true &&
                quinta == true && sexta == true && sabado == true)
        {
            return "Todos os dias";
        }
        else if(domingo == false && segunda == true && terca == true && quarta == true &&
                quinta == true && sexta == true && sabado == false)
        {
            return "Dias de Semana";
        }
        else if(domingo == true && segunda == false && terca == false && quarta == false &&
                quinta == false && sexta == false && sabado == true)
        {
            return "Final de Semana";
        }
        else{
            String dom = "";
            String seg = "";
            String ter = "";
            String quar = "";
            String quin = "";
            String sext = "";
            String sab = "";

            if(domingo == true){
                dom = "domingo";
            }
            if(segunda == true){
                seg = "segunda";
            }
            if(terca == true){
                ter = "terca";
            }
            if(quarta == true){
                quar = "quarta";
            }
            if(quinta == true){
                quin = "quinta";
            }
            if(sexta == true){
                sext = "sexta";
            }
            if(sabado == true){
                sab = "sabado";
            }


            String text = dom+":"+seg+":"+ter+":"+
            quar+":"+quin+":"+sext+":"+sab;
            return text;
        }
    }

}