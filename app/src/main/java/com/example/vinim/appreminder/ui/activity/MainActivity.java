package com.example.vinim.appreminder.ui.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.vinim.appreminder.R;
import com.example.vinim.appreminder.dao.AlarmeDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) { //AO CRIAR CICLO: https://i.imgur.com/L5AC1gX.png
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("App Reminder2");
        FloatingActionButton botaoMudar = findViewById(R.id.activity_main_botao_adicionar);

        //listener para escutar o botao
        botaoMudar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                criandoAlarmeActivity();
            }
        });
    }
    public void criandoAlarmeActivity(){
        Intent createAlarme = new Intent(this, criandoAlarmeActivity.class);
        startActivity(createAlarme);
    }

    @Override //AO VOLTAR CICLO: https://i.imgur.com/L5AC1gX.png
    protected void onResume() {
        super.onResume();

        AlarmeDAO dao = new AlarmeDAO();
        //Pegando o id de uma lista.
        ListView ListaDeAlarmes = findViewById(R.id.activity_main_alarmes);
        //(this , o layout de cada item, a lista)
        ListaDeAlarmes.setAdapter(new ArrayAdapter<>(this, R.layout.teste, dao.todosAlarmes()));
    }
}

