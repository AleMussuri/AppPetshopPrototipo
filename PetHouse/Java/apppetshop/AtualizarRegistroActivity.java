package com.example.apppetshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AtualizarRegistroActivity extends AppCompatActivity {

    private EditText inf;
    private RegistroDAO dao;
    private Registro registro = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_registro);

        inf = findViewById(R.id.attregis);
        dao = new RegistroDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("registro")){
            registro = (Registro) it.getSerializableExtra("registro");
            inf.setText(registro.getInf());

        }
    }

    public void AtualizarConsulta(View view) {

            registro.setIdpaciente(inf.getText().toString());
            dao.atualizar(registro);
            Toast.makeText(this, "Registro Atualizado com sucesso!!", Toast.LENGTH_SHORT).show();

    }

}