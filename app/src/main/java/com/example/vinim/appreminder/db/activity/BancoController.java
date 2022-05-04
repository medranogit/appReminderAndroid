package com.example.vinim.appreminder.db.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class BancoController {

    private SQLiteDatabase db;
    private PrepararBanco banco;
    private byte[] bool;
    private Object Integer;

    public BancoController(Context context) {
        banco = new PrepararBanco(context); }


    public void insereDadosREMEDIO(String nRemedio){
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("nRemedio",nRemedio);

        resultado = db.insert("REMEDIO", null, valores);
        db.close(); }

    public Cursor ARRUMARconsultaDadosREMEDIO(String nRemedio){
        Cursor cursor;
        String[] campos = { "nRemedio" };
        db = banco.getReadableDatabase();
        cursor = db.query("REMEDIO", campos,null, null, null, null, null);
        if
                (cursor != null) { cursor.moveToFirst();}
        db.close();
        return cursor;
    }

    public void insereDadosRECEITA(String descricao) {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("descricao", descricao);

        resultado = db.insert("RECEITA", null, valores);
        db.close();
    }

    public Cursor ARRUMARconsultaDadosRECEITA(String codigo, String descricao){
        Cursor cursor;
        String[] campos = { "codigo", "descricao" };
        db = banco.getReadableDatabase();
        cursor = db.query("RECEITA", campos,null, null, null, null, null);
        if
                (cursor != null) { cursor.moveToFirst();}
        db.close();
        return cursor;

    }

    public void insereDadosALARME(Integer hora, Integer minuto, String vibrar, String domingo,
                                  String segunda, String terca, String quarta, String quinta, String sexta, String sabado, String state)
    {
        ContentValues valores;
        long resultado;
        db = banco.getWritableDatabase();

        valores = new ContentValues();
        valores.put("hora", hora);
        valores.put("minute", minuto);
        valores.put("vibrar", vibrar);
        valores.put("domingo", domingo);
        valores.put("segunda", segunda);
        valores.put("terca", terca);
        valores.put("quarta", quarta);
        valores.put("quinta", quinta);
        valores.put("sexta", sexta);
        valores.put("sabado", sabado);
        valores.put("state", state);

        resultado = db.insert("ALARME", null, valores);
        db.close(); }



    public Cursor ArrumarconsultaDadosREMEDIO(String codigo, String hora){
        Cursor cursor;
        String[] campos = {"codigo", "hora", "minute", "vibrar", "domingo", "segunda", "terca",
                "quarta", "quinta", "sexta", "sabado"};
        db = banco.getReadableDatabase();
        cursor = db.query("ALARME", campos,null, null, null, null, null);
        if
                (cursor != null) { cursor.moveToFirst();}
        db.close();
        return cursor;
    }

    public Cursor getDadosIdAlarme(int id){
        Cursor cursor;
        String[] campos = {"idTable", "hora", "minute", "vibrar", "domingo",
                "segunda", "terca", "quarta", "quinta", "sexta", "sabado", "state"};

        id = id+1;
        String where = "idTable = " + id;

        db = banco.getReadableDatabase();

        cursor = db.query("ALARME", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public Cursor getDadosIdRemedio(int id){
        Cursor cursor;
        String[] campos = {"nRemedio"};

        id = id+1;
        String where = "idTable = " + id;

        db = banco.getReadableDatabase();

        cursor = db.query("REMEDIO", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor getDadosIdReceita(int id){
        Cursor cursor;
        String[] campos = {"descricao"};

        id = id+1;
        String where = "idTable = " + id;

        db = banco.getReadableDatabase();


        cursor = db.query("RECEITA", campos, where, null, null, null,
                null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }


    public Cursor retonarQuantosAlarmesTemEmInteiro(){
        Cursor cursor;
        String countQuery = "SELECT  * FROM ALARME ";;
        db = banco.getReadableDatabase();

        cursor = db.rawQuery(countQuery, null);
        return cursor;
    }

    public String alteraDadoAlarme(int id, Integer hora, Integer minuto, String vibrar,
                                   String domingo, String segunda, String terca, String quarta,
                                   String quinta, String sexta, String sabado, String state){

        String msg = "Dados alterados com sucesso!!!" ;

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues() ;

        valores.put("hora" , hora ) ;
        valores.put("minute", minuto) ;
        valores.put("vibrar", vibrar) ;
        valores.put("domingo", domingo) ;
        valores.put("segunda", segunda) ;
        valores.put("terca", terca) ;
        valores.put("quarta", quarta) ;
        valores.put("quinta", quinta) ;
        valores.put("sexta", sexta) ;
        valores.put("sabado", sabado) ;
        valores.put("state", state) ;

        String condicao = "idTable = " + id ;

        int linha ;
        linha = db.update("ALARME", valores, condicao, null) ;

        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }

        db.close() ;
        return msg;
    }

    public String alteraDadoReceita(int id, String descricao){

        String msg = "Dados alterados com sucesso!!!" ;

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues() ;

        valores.put("descricao" , descricao ) ;

        String condicao = "idTable = " + id ;

        int linha ;
        linha = db.update("RECEITA", valores, condicao, null) ;

        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }

        db.close() ;
        return msg;
    }

    public String alteraDadoRemedio(int id, String remedio){

        String msg = "Dados alterados com sucesso!!!" ;

        db = banco.getReadableDatabase();

        ContentValues valores = new ContentValues() ;

        valores.put("nRemedio" , remedio) ;

        String condicao = "idTable = " + id ;

        int linha ;
        linha = db.update("REMEDIO", valores, condicao, null) ;

        if (linha < 1){
            msg = "Erro ao alterar os dados" ;
        }

        db.close() ;
        return msg;
    }

}