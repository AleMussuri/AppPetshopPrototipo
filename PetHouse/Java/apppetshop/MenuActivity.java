package com.example.apppetshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }
    public void gestprod (View view) {
        startActivity(new Intent(MenuActivity.this, ListProdActivity.class));

    }
    public void gestpaciente (View view) {
        startActivity(new Intent(MenuActivity.this, ListarPacienteActivity.class));

    }
    public void consultagest (View view) {
        startActivity(new Intent(MenuActivity.this, ListConsultaActivity.class));

    }
}