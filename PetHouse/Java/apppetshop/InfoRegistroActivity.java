package com.example.apppetshop;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoRegistroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inforegistro);

        Intent intent = getIntent();

        String info =(String) intent.getSerializableExtra("inf");



        TextView inf = (TextView) findViewById(R.id.txtdesc);


        inf.setText(info);

    }

}