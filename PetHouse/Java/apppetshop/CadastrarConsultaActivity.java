package com.example.apppetshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class CadastrarConsultaActivity extends AppCompatActivity {

    private EditText idpaciente;
    private EditText agendamento;
    private EditText pago;
    private ConsultaDAO dao;
    private Consulta consulta = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_consulta);

        idpaciente = findViewById(R.id.txtanotacao);
        agendamento = findViewById(R.id.agendamento);
        pago = findViewById(R.id.pago);
        dao = new ConsultaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("consulta")){
            consulta = (Consulta) it.getSerializableExtra("consulta");
            idpaciente.setText(consulta.getIdpaciente());
            agendamento.setText(consulta.getAgendamento());
            pago.setText(consulta.getPago());

        }
    }

    public void Cadastrar(View view) {
            consulta = new Consulta();
            consulta.setIdpaciente(idpaciente.getText().toString());
            consulta.setAgendamento(agendamento.getText().toString());
            consulta.setPago(pago.getText().toString());
            long idconsulta = dao.inserirConsulta(consulta);
            Toast.makeText(this, "Consulta inserida com codigo: " + idconsulta, Toast.LENGTH_SHORT).show();
    }

}