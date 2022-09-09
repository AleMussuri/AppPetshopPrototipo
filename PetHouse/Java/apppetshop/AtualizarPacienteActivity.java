package com.example.apppetshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AtualizarPacienteActivity extends AppCompatActivity {

    private EditText cpf;
    private EditText nome;
    private EditText endereco;
    private EditText contato;
    private EditText nomepet;
    private EditText racapet;
    private EditText idadepet;
    private PacienteDAO dao;
    private Paciente paciente = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_paciente);
        cpf = findViewById(R.id.attcpf);
        nome = findViewById(R.id.attnome);
        endereco = findViewById(R.id.attendereco);
        contato = findViewById(R.id.attcont);
        nomepet = findViewById(R.id.attnomepet);
        racapet = findViewById(R.id.attracapet);
        idadepet = findViewById(R.id.attidadepet);

        dao = new PacienteDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("paciente")){
            paciente = (Paciente) it.getSerializableExtra("paciente");
            cpf.setText(paciente.getCpf());
            nome.setText(paciente.getNome());
            endereco.setText(paciente.getEndereco());
            contato.setText(paciente.getContato());
            nomepet.setText(paciente.getNomepet());
            racapet.setText(paciente.getRacapet());
            idadepet.setText(paciente.getIdadepet());


        }
    }

    public void AtualizarPaciente(View view) {
        paciente.setCpf(cpf.getText().toString());
        paciente.setNome(nome.getText().toString());
        paciente.setEndereco(endereco.getText().toString());
        paciente.setContato(contato.getText().toString());
        paciente.setNomepet(nomepet.getText().toString());
        paciente.setRacapet(racapet.getText().toString());
        paciente.setIdadepet(idadepet.getText().toString());
        dao.atualizar(paciente);
        Toast.makeText(this, "Paciente Atualizado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

