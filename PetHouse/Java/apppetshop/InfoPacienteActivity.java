package com.example.apppetshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoPacienteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_paciente);

        Intent intent = getIntent();

        String nomepaci=(String) intent.getSerializableExtra("nome");
        String ende =(String) intent.getSerializableExtra("endereco");
        String cont =(String) intent.getSerializableExtra("contato");
        String pet =(String) intent.getSerializableExtra("nomepet");
        String raca =(String) intent.getSerializableExtra("racapet");
        String idade =(String) intent.getSerializableExtra("idadepet");


        TextView nome = (TextView) findViewById(R.id.txtanotacao);
        TextView endereco = (TextView) findViewById(R.id.txtendereco);
        TextView contato = (TextView) findViewById(R.id.txtcont);
        TextView nomepet = (TextView) findViewById(R.id.txttnomepet);
        TextView racapet = (TextView) findViewById(R.id.txtacapet);
        TextView idadepet = (TextView) findViewById(R.id.txtidadepet);



        nome.setText(nomepaci);
        endereco.setText(ende);
        contato.setText(cont);
        nomepet.setText(pet);
        racapet.setText(raca);
        idadepet.setText(idade);



    }

}