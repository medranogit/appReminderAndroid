package com.example.vinim.appreminder.ui.activity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.vinim.appreminder.R;
import com.example.vinim.appreminder.dao.AlarmeDAO;

import java.util.Locale;


public class criandoAlarmeActivity extends AppCompatActivity {

    private EditText campoRemedio;
    private EditText campoDescricao;
    private Button timeButton;
    private Switch campoVibrar;
    private RadioGroup radioGroupDiasSemana;
    private CheckBox segunda,terca,quarta,quinta,sexta,sabado,domingo;
    private Boolean segundaBool,tercaBool,quartaBool,quintaBool,sextaBool,sabadoBool,domingoBool;
    private boolean vibrarBool;
    //Chamando classe para salvar.
    final AlarmeDAO dao = new AlarmeDAO();
    int hour, minute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criando_alarme);

        setTitle("Criar Alarme");
        //Pegando Valores Formulario
        pegandoValoresFormulario();
        //Botao para criar o alarme
        botaoCriar();
    }

    private void pegandoValoresFormulario() {
        campoRemedio = findViewById(R.id.activity_criando_remedio);
        campoDescricao = findViewById(R.id.activity_criando_descricao);
        campoVibrar = findViewById(R.id.activity_switch_vibrar);
        radioGroupDiasSemana = findViewById(R.id.activity_repeticao);
        pegandoValoresFormularioDiasSemana();
    }

    private void pegandoValoresFormularioDiasSemana(){
        segunda = findViewById(R.id.radiogroup_diaEspecifico_segunda);
        terca = findViewById(R.id.radiogroup_diaEspecifico_terça);
        quarta = findViewById(R.id.radiogroup_diaEspecifico_quarta);
        quinta = findViewById(R.id.radiogroup_diaEspecifico_quinta);
        sexta = findViewById(R.id.radiogroup_diaEspecifico_sexta);
        sabado = findViewById(R.id.radiogroup_diaEspecifico_sabado);
        domingo = findViewById(R.id.radiogroup_diaEspecifico_domingo);
        setClickableButtonGroup(false);
    }

    private void botaoCriar() {
        //Chamando o botao do xml
        Button botaoSalvar = findViewById(R.id.activity_botao_salvar);
        //listener para escutar o botao
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Alarme alarmeCriado = criaAlarme(campoRemedio, campoDescricao);
                Toast.makeText(criandoAlarmeActivity.this, alarmeCriado.getDias(), Toast.LENGTH_SHORT).show();
                salva(alarmeCriado, dao);
                //Toast.makeText(criandoAlarmeActivity.this, alarmeCriado.getRemedio()  , Toast.LENGTH_SHORT).show();
            }
        });
    }
    @NonNull
    private Alarme criaAlarme(EditText campoRemedio, EditText campoDescricao){
        //armazenando o texto em novas variaveis
        String remedio = campoRemedio.getText().toString();
        String descricao = campoDescricao.getText().toString();

        //Estrutura condicional para ver o estado do Switch passando para um bool.
        if(campoVibrar.isChecked()){
            vibrarBool = true;
        }else {
            vibrarBool = false;
        }

        verificando_repeticao();

        //Toast.makeText(this, repeticaoValor, Toast.LENGTH_SHORT).show();
        return new Alarme(remedio, descricao, hour, minute, vibrarBool, domingoBool, segundaBool,
                tercaBool, quartaBool, quintaBool, sextaBool, sabadoBool);
    }



    private String radioGroupConversion(){
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

    private void salva(Alarme alarmeCriado, AlarmeDAO dao) {
        dao.salva(alarmeCriado);
        //Mata essa activity e retorna para a anterior
        finish();
    }

    public void salvarHora(View view) {
        timeButton = findViewById(R.id.activity_criando_horario);
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minute = selectedMinute;
                timeButton.setText(String.format(Locale.getDefault(), "%02d:%02d", hour, minute));
            }
        };

        int style = AlertDialog.THEME_HOLO_DARK;
        TimePickerDialog relogio = new TimePickerDialog(this, style , onTimeSetListener, hour, minute, true);
        relogio.setTitle("Selecione a Hora");
        relogio.show();
    }

    private void setClickableButtonGroup(Boolean valor){
        if(valor == true){
            segunda.setEnabled(true);
            terca.setEnabled(true);
            quarta.setEnabled(true);
            quinta.setEnabled(true);
            sexta.setEnabled(true);
            sabado.setEnabled(true);
            domingo.setEnabled(true);

        }else{
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

    public void verificacao_radioButtons(View view) {
        String s1 = radioGroupConversion();
        String s2 = "Dias manuais";

        if(s1.equals(s2)){
            setClickableButtonGroup(true);
        }else {
            setClickableButtonGroup(false);
        }
    }

    private void verificando_repeticao() {
        String repeticaoValor = radioGroupConversion();

        if(repeticaoValor.equals("Todo dia")){
            segundaBool = true; tercaBool = true; quartaBool = true; quintaBool = true;
            sextaBool = true; sabadoBool = true; domingoBool = true;
        }
        else if (repeticaoValor.equals("Dias de Semana")){
            segundaBool = true; tercaBool = true; quartaBool = true; quintaBool = true;
            sextaBool = true; sabadoBool = false; domingoBool = false;
        }
        else if(repeticaoValor.equals("Final de Semana")){
            segundaBool = false; tercaBool = false; quartaBool = false; quintaBool = false;
            sextaBool = false; sabadoBool = true; domingoBool = true;
        }else if(repeticaoValor.equals("Dias manuais")){
            if(segunda.isChecked()){
                segundaBool = true;
            }else {
                segundaBool = false;
            }

            if(terca.isChecked()){
                tercaBool = true;
            }else {
                tercaBool = false;
            }

            if(quarta.isChecked()){
                quartaBool = true;
            }else {
                quartaBool = false;
            }

            if(quinta.isChecked()){
                quintaBool = true;
            }else {
                quintaBool = false;
            }

            if(sexta.isChecked()){
                sextaBool = true;
            }else {
                sextaBool = false;
            }

            if(sabado.isChecked()){
                sabadoBool = true;
            }else {
                sabadoBool = false;
            }

            if(domingo.isChecked()){
                domingoBool = true;
            }else {
                domingoBool = false;
            }
        }

    }

}
