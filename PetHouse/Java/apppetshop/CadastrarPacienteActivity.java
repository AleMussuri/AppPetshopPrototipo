package com.example.apppetshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarPacienteActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_cadastrar_paciente);

        cpf = findViewById(R.id.attcpf);
        nome = findViewById(R.id.att);
        endereco = findViewById(R.id.digitaendereco);
        contato = findViewById(R.id.digitacont);
        nomepet = findViewById(R.id.digitnomepet);
        racapet = findViewById(R.id.digitaracapet);
        idadepet = findViewById(R.id.digitaidadepet);
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

    public void CadastrarPaciente(View view) {
            paciente = new Paciente();
            paciente.setCpf(cpf.getText().toString());
            paciente.setNome(nome.getText().toString());
            paciente.setEndereco(endereco.getText().toString());
            paciente.setContato(contato.getText().toString());
            paciente.setNomepet(nomepet.getText().toString());
            paciente.setRacapet(racapet.getText().toString());
            paciente.setIdadepet(idadepet.getText().toString());
            long idp = dao.CadastrarPaciente(paciente);
            Toast.makeText(this, "Paciente Cadastrado: " + idp, Toast.LENGTH_SHORT).show();

    }
}