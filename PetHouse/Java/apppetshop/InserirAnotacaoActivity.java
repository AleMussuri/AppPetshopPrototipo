package com.example.apppetshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class InserirAnotacaoActivity extends AppCompatActivity {

    private EditText anotacao;
    private ConsultaDAO dao;
    private Consulta consulta = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_anotacao);

        anotacao = findViewById(R.id.txtanotacao);
        dao = new ConsultaDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("consulta")){
            consulta = (Consulta) it.getSerializableExtra("consulta");
            anotacao.setText(consulta.getAnotacao());


        }
    }

    public void AtualizarConsultAnotacao(View view) {
            consulta.setAnotacao(anotacao.getText().toString());
            dao.inserirAnotacao(consulta);
            Toast.makeText(this, "Anotação Atualizada com sucesso!!", Toast.LENGTH_SHORT).show();
    }

}