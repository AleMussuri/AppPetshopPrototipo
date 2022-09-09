package com.example.apppetshop;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Conexão extends SQLiteOpenHelper {

    private static final String name = "banco.db";
    private static final int version = 1;

    public Conexão(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table produto(codigo integer primary key autoincrement," +
                "nome varchar(50), preco varchar(50), descricao varchar(500))");

        db.execSQL("create table paciente(cpf integer primary key autoincrement," +
                "nome varchar(50), endereco varchar(50), contato varchar(500), nomepet varchar(50), racapet varchar(50), idadepet varchar(50))");


        String SQL = "CREATE TABLE consulta(idconsulta integer primary key autoincrement,idpaciente integer, agendamento datetime," +
                "pago varchar(1),anotacao varchar(256), FOREIGN KEY (idpaciente) REFERENCES paciente(cpf))";
        db.execSQL(SQL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
