package com.example.vinim.appreminder.db.activity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PrepararBanco extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "appReminderBD";
    private static final int VERSAO = 28;

    public PrepararBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS REMEDIO");
        db.execSQL("DROP TABLE IF EXISTS RECEITA");
        db.execSQL("DROP TABLE IF EXISTS ALARME");
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE REMEDIO ("
                + "idTable integer primary key autoincrement,"
                + "nRemedio string)";

        db.execSQL(sql);
        String sql1 = "CREATE TABLE RECEITA ("
                + "idTable integer primary key autoincrement,"
                + "descricao string)";

        db.execSQL(sql1);

        String sql2 = "CREATE TABLE ALARME ("
                + "idTable integer primary key autoincrement,"
                + "hora int,"
                + "minute int,"
                + "vibrar string,"
                + "domingo string,"
                + "segunda string,"
                + "terca string,"
                + "quarta string,"
                + "quinta string,"
                + "sexta string,"
                + "sabado string,"
                + "state string)";

        db.execSQL(sql2);
    }


}
