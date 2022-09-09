package com.example.apppetshop;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class InfoConsultaActivity extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoconsulta);

        Intent intent = getIntent();

        String paciente =(String) intent.getSerializableExtra("nome");
        String pet =(String) intent.getSerializableExtra("nomepet");
        String raca =(String) intent.getSerializableExtra("racapet");
        String idade =(String) intent.getSerializableExtra("idadepet");
        String data =(String) intent.getSerializableExtra("agendamento");
        String info =(String) intent.getSerializableExtra("inf");

        TextView nome = (TextView) findViewById(R.id.txtpaciente);
        TextView nomepet = (TextView) findViewById(R.id.txtnomepet);
        TextView racapet = (TextView) findViewById(R.id.txtraca);
        TextView idadepet = (TextView) findViewById(R.id.txtidade);
        TextView agendamento = (TextView) findViewById(R.id.txtdata);
        TextView obs = (TextView) findViewById(R.id.info);

        nome.setText(paciente);
        nomepet.setText(pet);
        racapet.setText(raca);
        idadepet.setText(idade);
        agendamento.setText(data);
        obs.setText(info);

    }

    public void visualizar (View view) {
        startActivity(new Intent(InfoConsultaActivity.this, ListRegistroActivity.class));
    }

}