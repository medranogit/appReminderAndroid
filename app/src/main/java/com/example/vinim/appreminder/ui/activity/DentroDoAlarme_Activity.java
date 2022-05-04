package com.example.vinim.appreminder.ui.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vinim.appreminder.R;
import com.example.vinim.appreminder.db.activity.BancoController;

import java.util.Locale;

public class DentroDoAlarme_Activity extends AppCompatActivity {

    Alarme alarmeSelecionado;
    BancoController bd;
    TextView horaAlarme;
    int hora, minuto;
    Switch vibrarALarme,onAndOffSwitch;
    RadioButton todoDia, diasDeSemana, finaldesemana, diasEspecificos;
    EditText descriAlarme, remedioAlarme;
    CheckBox domingo, segunda, terca, quarta, quinta, sexta, sabado;
    Button timeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oficial_activity_dentro_do_alarme_);

        iniciandoComponentes();
        pegandoClasseNaLista();
        setandoOsValores();

    }

    private void pegandoClasseNaLista() {
        Intent passadoIntent = getIntent();
        String passadoStringID = passadoIntent.getStringExtra("id");
        //ACHANDO A CLASSE NA LISTA PPOR POSIÇÃO DO ID DO INTENT ANTERIOR
        alarmeSelecionado = MainActivity.alarmeList.get(Integer.valueOf(passadoStringID));

    }

    private void iniciandoComponentes(){
        horaAlarme = (TextView) findViewById(R.id.activity_dentroDoAlarme_hora);
        remedioAlarme = (EditText) findViewById(R.id.activity_dentroDoAlarme_nomeRemedio);
        descriAlarme = (EditText) findViewById(R.id.activity_dentroDoAlarme_descri);
        vibrarALarme = (Switch) findViewById(R.id.activity_dentroDoAlarme_vibrar);
        onAndOffSwitch = (Switch) findViewById(R.id.activity_dentroDoAlarme_onAndOff);

        //REPETICAO
        todoDia = findViewById(R.id.radiogroup_todoDia);
        diasDeSemana = findViewById(R.id.radiogroup_diaDaSemana);
        finaldesemana = findViewById(R.id.radiogroup_fimDeSemana);
        diasEspecificos = findViewById(R.id.radiogroup_diaEspecifico);

        domingo = findViewById(R.id.radiogroup_diaEspecifico_domingo);
        segunda = findViewById(R.id.radiogroup_diaEspecifico_segunda);
        terca = findViewById(R.id.radiogroup_diaEspecifico_terça);
        quarta = findViewById(R.id.radiogroup_diaEspecifico_quarta);
        quinta = findViewById(R.id.radiogroup_diaEspecifico_quinta);
        sexta = findViewById(R.id.radiogroup_diaEspecifico_sexta);
        sabado = findViewById(R.id.radiogroup_diaEspecifico_sabado);
    }

    //Chamando os valores ao abrir
    private void setandoOsValores() {

        //Toast.makeText(this, alarmeSelecionado.getId(), Toast.LENGTH_SHORT).show();

        //HORA
        horaAlarme.setText(alarmeSelecionado.getTime());
        //REMEDIO
        remedioAlarme.setText(alarmeSelecionado.getRemedio());
        //DESCRICAO
        descriAlarme.setText(alarmeSelecionado.getDescricao());
        //ON and OFF
        Boolean stringToBoleanONOFF = Boolean.parseBoolean(alarmeSelecionado.getState());
        onAndOffSwitch.setChecked(stringToBoleanONOFF);

        //VIBRAR
        vibrarALarme.setChecked(alarmeSelecionado.getVibrar());
        //REPETICAO

        if(alarmeSelecionado.getDiasSemana().equals("Todos os dias")){
            todoDia.setChecked(true);
            buttonCheckState(false);
        }else if(alarmeSelecionado.getDiasSemana().equals("Dias de Semana")){
            diasDeSemana.setChecked(true);
            buttonCheckState(false);
        }else if(alarmeSelecionado.getDiasSemana().equals("Final de Semana")){
            finaldesemana.setChecked(true);
            buttonCheckState(false);
        }else{
            diasEspecificos.setChecked(true);
            buttonCheckState(true);
            String diasText = alarmeSelecionado.getDiasSemana();
            String[] diasTextSplited = diasText.split(":");

            //For para verificar o split.
            for (int x = 0; x < diasTextSplited.length; x++) {
                if(diasTextSplited[x].equals("domingo")){
                    domingo.setChecked(true);
                }else if(diasTextSplited[x].equals("segunda")){
                    segunda.setChecked(true);
                }else if(diasTextSplited[x].equals("terca")){
                    terca.setChecked(true);
                }else if(diasTextSplited[x].equals("quarta")){
                    quarta.setChecked(true);
                }else if(diasTextSplited[x].equals("quinta")){
                    quinta.setChecked(true);
                }else if(diasTextSplited[x].equals("sexta")){
                    sexta.setChecked(true);
                }else if(diasTextSplited[x].equals("sabado")){
                    sabado.setChecked(true);
                }
            }
        }


    }
    //CHAMANDO
    //Salvando os valores ao pressionar o botao salvar.
    public void salvar(View view){
        Boolean finish1 = false, finish2 = false;

        //HORA
        String textHora = horaAlarme.getText().toString();
        if(textHora.equals(alarmeSelecionado.getTime())== false){
            alarmeSelecionado.setTime(textHora);
            //Toast.makeText(this, "NotEqual", Toast.LENGTH_SHORT).show();
        }

        // ON/OFF SWITCH
        if(onAndOffSwitch.isChecked()){
            alarmeSelecionado.setState("ON");
        }else {
            alarmeSelecionado.setState("OFF");
        }

        //vibrarSet
        if(vibrarALarme.isChecked()){
            alarmeSelecionado.setVibrar(true);
        }else {
            alarmeSelecionado.setVibrar(false);
        }

        //REMEDIO NOME
        String remedioTextInput = remedioAlarme.getText().toString();
        if(remedioTextInput.length() < 1){
            Toast.makeText(this, "Coloque um nome para o remédio", Toast.LENGTH_SHORT).show();
        }else if(remedioTextInput.length() > 18){
            Toast.makeText(this, "Nome do remédio excede o limite de caracteres.", Toast.LENGTH_SHORT).show();
        }
        else{
            if(remedioAlarme.equals(alarmeSelecionado.getRemedio())==false){
                alarmeSelecionado.setRemedio(remedioTextInput);
            }
            finish2 = true;
        }


        //DESCRICAO
        if(descriAlarme.equals(alarmeSelecionado.getDescricao())==false){
            String text = descriAlarme.getText().toString();
            alarmeSelecionado.setDescricao(text);
        }




        //DIAS REPETICAO
        String stringRadialButton = radioGroupToString();

        if(stringRadialButton.equals("Todo dia")){
            alarmeSelecionado.setDiasSemana(true, true, true, true, true, true, true);
            finish1 = true;
        }
        else if (stringRadialButton.equals("Dias de Semana")){
            alarmeSelecionado.setDiasSemana(false, true, true, true, true, true, false);
            finish1 = true;
        }
        else if(stringRadialButton.equals("Final de Semana")){
            alarmeSelecionado.setDiasSemana(true, false, false, false, false, false, true);
            finish1 = true;
        }else{
            Boolean domingoBool = false, segundaBool = false, tercaBool = false, quartaBool = false, quintaBool = false,
                    sextaBool = false, sabadoBool = false;

            if(domingo.isChecked()){
                domingoBool = true;
            }
            if(segunda.isChecked()){
                segundaBool = true;
            }
            if(terca.isChecked()){
                tercaBool = true;
            }
            if(quarta.isChecked()){
                quartaBool = true;
            }
            if(quinta.isChecked()){
                quintaBool = true;
            }
            if(sexta.isChecked()){
                sextaBool = true;
            }
            if(sabado.isChecked()){
                sabadoBool = true;
            }

            if(domingo.isChecked()==false && segunda.isChecked()==false && terca.isChecked()==false
                    && quarta.isChecked()==false && quinta.isChecked()==false && sexta.isChecked()==false && sabado.isChecked()==false){
                Toast.makeText(this, "Coloque ao menos um dia especifico", Toast.LENGTH_SHORT).show();
            }else{
                alarmeSelecionado.setDiasSemana(domingoBool, segundaBool, tercaBool, quartaBool, quintaBool, sextaBool, sabadoBool);
                finish1 = true;
            }

        }

        if(finish1 == true && finish2 == true){
            //INSERTS BD
            //SEM ID // ADICIONA HORA/MINUTO, VIBRAR,
            updateDP(horaAlarme.toString());
            finish();
        }

    }

    private void updateDP(String horaAlarme1){
        bd = new BancoController(getBaseContext());
        //Pegando ID classe
        Integer stringIdToInt = Integer.parseInt(alarmeSelecionado.getId());




        //VARIAVEIS E TRATAMENTOS
        String textHora1 = horaAlarme.getText().toString();
        String[] textHora1Splite = textHora1.split(":");
        Integer hora2 = Integer.parseInt(textHora1Splite[0]);
        Integer minute2 = Integer.parseInt(textHora1Splite[1]);
        String vibrar2 = "false";
        String domingo2 = "false", segunda2 = "false", terca2 = "false", quarta2 = "false", quinta2 = "false",
                sexta2 = "false", sabado2 = "false";
        String stringRadialButton = radioGroupToString();
        String state2 = "OFF";

        // ON/OFF SWITCH
        if(onAndOffSwitch.isChecked()){
            state2 = "ON";
        }


        //VIBRAR
        if(vibrarALarme.isChecked()){
            vibrar2 = "true";
        }

        //REPETICAO
        if(stringRadialButton.equals("Todo dia")){
            domingo2 = "true"; segunda2 = "true"; terca2 = "true"; quarta2 = "true"; quinta2 = "true";
                    sexta2 = "true"; sabado2 = "true";

        }
        else if (stringRadialButton.equals("Dias de Semana")){
            domingo2 = "false"; segunda2 = "true"; terca2 = "true"; quarta2 = "true"; quinta2 = "true";
            sexta2 = "true"; sabado2 = "false";

        }
        else if(stringRadialButton.equals("Final de Semana")){
            domingo2 = "true"; segunda2 = "false"; terca2 = "false"; quarta2 = "false"; quinta2 = "false";
            sexta2 = "false"; sabado2 = "true";

        }else {

            if(domingo.isChecked()){
                domingo2 = "true";
            }
            if(segunda.isChecked()){
                segunda2 = "true";
            }
            if(terca.isChecked()){
                terca2 = "true";
            }
            if(quarta.isChecked()){
                quarta2 = "true";
            }
            if(quinta.isChecked()){
                quinta2 = "true";
            }
            if(sexta.isChecked()){
                sexta2 = "true";
            }
            if(sabado.isChecked()){
                sabado2 = "true";
            }
        }

        bd.alteraDadoAlarme(stringIdToInt+1, hora2 , minute2, vibrar2, domingo2, segunda2,
               terca2, quarta2, quinta2, sexta2, sabado2,state2); //WORK

        //REMEDIO NOME
        if(remedioAlarme.equals(alarmeSelecionado.getRemedio())==false){
            String textRemed = remedioAlarme.getText().toString();

            bd.alteraDadoRemedio(stringIdToInt+1, textRemed);
        }

        //DESCRICAO
        if(descriAlarme.equals(alarmeSelecionado.getDescricao())==false){
            String textDesc = descriAlarme.getText().toString();

            bd.alteraDadoReceita(stringIdToInt+1,textDesc);
        }


    }

    private String radioGroupToString(){
        RadioGroup radioGroupDiasSemana = findViewById(R.id.activity_dentroDoAlarme_radiogroup);
        int itemRadioGroupSelecionado = radioGroupDiasSemana.getCheckedRadioButtonId();

        if(itemRadioGroupSelecionado != -1){
            RadioButton rbOpçaoSelecionada = findViewById(itemRadioGroupSelecionado);
            String rbOpçaoSelecionadaTexto = rbOpçaoSelecionada.getText().toString();
            //Toast.makeText(this, rbOpçaoSelecionadaTexto, Toast.LENGTH_SHORT).show();
            return rbOpçaoSelecionadaTexto;
        }else{
            return "";
        }
    }

    public void acessorRadioButtonCheck(View view) {
        String stringRadialButton = radioGroupToString();

        //Toast.makeText(this, stringRadialButton, Toast.LENGTH_SHORT).show();
        if(stringRadialButton.equals("Dias Especificos")){
            buttonCheckState(true);
        }else{
            buttonCheckState(false);
        }
    }

    private void buttonCheckState(Boolean state){
        if(state == true){
            segunda.setEnabled(true);
            terca.setEnabled(true);
            quarta.setEnabled(true);
            quinta.setEnabled(true);
            sexta.setEnabled(true);
            sabado.setEnabled(true);
            domingo.setEnabled(true);
        }else {
            segunda.setChecked(false);
            segunda.setEnabled(false);
            terca.setChecked(false);
            terca.setEnabled(false);
            quarta.setChecked(false);
            quarta.setEnabled(false);
            quinta.setChecked(false);
            quinta.setEnabled(false);
            sexta.setChecked(false);
            sexta.setEnabled(false);
            sabado.setChecked(false);
            sabado.setEnabled(false);
            domingo.setChecked(false);
            domingo.setEnabled(false);
        }

    }


    public void escolherHora(View view) {
        timeButton = findViewById(R.id.activity_criando_horario);

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hora = selectedHour;
                minuto = selectedMinute;
                horaAlarme.setText(String.format(Locale.getDefault(), "%02d:%02d", hora, minuto));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog relogio = new TimePickerDialog(this, style , onTimeSetListener, hora, minuto, true);
        relogio.setTitle("Selecione a Hora");
        relogio.show();
    }
}
