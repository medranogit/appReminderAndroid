package com.example.vinim.appreminder.ui.activity;


import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vinim.appreminder.R;
import com.example.vinim.appreminder.db.activity.BancoController;


import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    BancoController bd;

    public static ArrayList<Alarme> alarmeList = new ArrayList<Alarme>();
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) { //AO CRIAR CICLO: https://i.imgur.com/L5AC1gX.png
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oficial_activity_main);

        //Para nao criar mais de uma vez sempre é dado um clear na lista caso seja ativado o create
        alarmeList.clear();

        instalarDados();
        installAndUpdateLista();
        instalarAoEscutarClick();
        //Toast.makeText(this, "create", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //PROCURAR NO BANCO
        installAndUpdateLista();
        //Toast.makeText(this, "resume", Toast.LENGTH_SHORT).show();
    }

    private void instalarDados() { //Isso ligar com o banco de dados for quantos alarmes tiver no sqlite

        bd = new BancoController(getBaseContext());
        Cursor dados2 = bd.retonarQuantosAlarmesTemEmInteiro();
        Integer tamanhoBancoDados = dados2.getCount();

        //Toast.makeText(this, tamanhoBancoDados.toString(), Toast.LENGTH_SHORT).show();
        for (int x = 0; x < tamanhoBancoDados ; x++){

            Cursor dadosAlarme = bd.getDadosIdAlarme(x);
            Cursor dadosRemedio = bd.getDadosIdRemedio(x);
            Cursor dadosReceita = bd.getDadosIdReceita(x);

            Integer id1 = dadosAlarme.getInt(0);
            id1 = id1-1;
            String id1toString = id1.toString();

            String nomeRemedio1 = dadosRemedio.getString(0);
            String hora1 = dadosAlarme.getString(1);
            String minuto1 = dadosAlarme.getString(2);

            Boolean vibrar1 = Boolean.parseBoolean(dadosAlarme.getString(3));
            Boolean domingo1 = Boolean.parseBoolean(dadosAlarme.getString(4));
            Boolean segunda1 = Boolean.parseBoolean(dadosAlarme.getString(5));
            Boolean terca1 = Boolean.parseBoolean(dadosAlarme.getString(6));
            Boolean quarta1 = Boolean.parseBoolean(dadosAlarme.getString(7));
            Boolean quinta1 = Boolean.parseBoolean(dadosAlarme.getString(8));
            Boolean sexta1 = Boolean.parseBoolean(dadosAlarme.getString(9));
            Boolean sabado1 = Boolean.parseBoolean(dadosAlarme.getString(10));
            String state1 = dadosAlarme.getString(11);

            String descricao1 = dadosReceita.getString(0);
            String hour0icrement = "";
            String minute0icrement = "";
            //0incrementTime
            if(hora1.length()<2){
                hour0icrement="0";
            }
            if(minuto1.length()<2){
                minute0icrement="0";
            }
            Alarme alarme =  new Alarme(id1toString, hour0icrement+hora1, minute0icrement+minuto1, nomeRemedio1,
                    descricao1, vibrar1, domingo1,
                    segunda1, terca1, quarta1, quinta1, sexta1, sabado1, state1);
            alarmeList.add(alarme);


        }
        //carregado pleo id começa no 1

    }
    private void installAndUpdateLista() {
        listView = (ListView) findViewById(R.id.main_activity_listView);
        AlarmeAdapter adapter = new AlarmeAdapter(getApplicationContext(), 0, alarmeList);
        listView.setAdapter(adapter);
    }

    private void instalarAoEscutarClick() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Alarme alarmeSelecionado = (Alarme) (listView.getItemAtPosition(position));

                Intent abrirAlarmeWindow = new Intent(getApplicationContext(), DentroDoAlarme_Activity.class);
                abrirAlarmeWindow.putExtra("id", alarmeSelecionado.getId());
                startActivity(abrirAlarmeWindow);
            }
        });
    }
    public void add_new_alarm(View view) {
        if(alarmeList.size() < 15){
            Integer id = alarmeList.size();
            Toast.makeText(this, "Alarme criado", Toast.LENGTH_SHORT).show();
            //PADRAO NOMES CRIACAO - MUDAR AQUI
            String nomeRemedio = "Remedio";
            String descricao = "";
            //CRIANDO CLASSE
            Alarme alarme =  new Alarme(id.toString(),"00", "00", nomeRemedio,
                    descricao, false, true,
                    true, true, true, true, true, true, "OFF");
            //INSERTS BD
            bd.insereDadosALARME(00,00,"true", "true", "true",
                    "true", "true", "true", "true", "true", "OFF");
            bd.insereDadosRECEITA(descricao);
            bd.insereDadosREMEDIO(nomeRemedio);
            //Adicionando a classe a lista.
            alarmeList.add(alarme);
            //Atualizando sempre após a criação de um item.
            installAndUpdateLista();
        }else {
            //Alerta caso chegue ao limite de alarmes.
            Toast.makeText(this, "Maximo de alarmes criado.", Toast.LENGTH_SHORT).show();
        }
    }
}

