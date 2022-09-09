package com.example.apppetshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {

    private Conexão conexao;
    private SQLiteDatabase banco;

    public RegistroDAO(Context context) {
        conexao = new Conexão(context);
        banco = conexao.getWritableDatabase();

    }

    public long inserirRegistro(Registro registro){
        ContentValues values = new ContentValues();
        values.put("inf", registro.getInf());
        values.put("idconsulta", registro.getIdconsulta());
        return banco.insert("registro", null,values);
    }

    public List<Registro> obterRegistros(){
        List<Registro> registros = new ArrayList<>();
        Cursor cursor = banco.rawQuery("SELECT * FROM registro INNER JOIN consulta ON registro.idconsulta = consulta.idconsulta", null);
        while(cursor.moveToNext()){
            Registro r1 = new Registro();
            r1.setIdregistro(cursor.getInt(0));
            r1.setInf(cursor.getString(1));
            r1.setIdconsulta(cursor.getInt(2));
            r1.setIdpaciente(cursor.getString(4));

            registros.add(r1);
        }
        return registros;

    }
    public void excluir(Registro r){
        banco.delete("registro", "idregistro= ?", new String[]{r.getIdregistro().toString()});
    }

    public void atualizar(Registro registro){
        ContentValues values = new ContentValues();
        values.put("inf", registro.getInf());
        banco.update("registro", values,
                "idregistro = ?", new String[]{registro.getIdregistro().toString()});
    }


}
