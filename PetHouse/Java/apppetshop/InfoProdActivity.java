package com.example.apppetshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InfoProdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforegistro);

        Intent intent = getIntent();

        String nomeprod =(String) intent.getSerializableExtra("nome");
        String valor =(String) intent.getSerializableExtra("preco");
        String desc =(String) intent.getSerializableExtra("descricao");

        TextView nome = (TextView) findViewById(R.id.txtanotacao);
        TextView preco = (TextView) findViewById(R.id.txtpreco);
        TextView descricao = (TextView) findViewById(R.id.txtdesc);


        nome.setText(nomeprod);
        preco.setText("R$: "+valor);
        descricao.setText(desc);
    }

}