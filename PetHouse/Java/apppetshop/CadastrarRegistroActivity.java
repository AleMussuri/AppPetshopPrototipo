package com.example.apppetshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class CadastrarRegistroActivity extends AppCompatActivity {

    private EditText pacienteregistro;
    private EditText inf;
    private RegistroDAO dao;
    private Registro registro = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_registro);

        inf = findViewById(R.id.attregis);
        pacienteregistro = findViewById(R.id.pacienteregistro);


        dao = new RegistroDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("registro")){
            registro = (Registro) it.getSerializableExtra("registro");
            inf.setText(registro.getInf());
            pacienteregistro.setText(registro.getIdconsulta());
        }
    }

    public void Salvar(View view) {
        String p = pacienteregistro.getText().toString();
        Registro registro = new Registro();
        registro.setInf(inf.getText().toString());
        registro.setIdconsulta(Integer.parseInt(p));
        long idregistro = dao.inserirRegistro(registro);
        Toast.makeText(this, "Registro inserida com codigo: " + idregistro, Toast.LENGTH_SHORT).show();
    }

}
