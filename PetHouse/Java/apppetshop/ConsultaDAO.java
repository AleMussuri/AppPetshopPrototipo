package com.example.apppetshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    private Conexão conexao;
    private SQLiteDatabase banco;

    public ConsultaDAO(Context context){
        conexao = new Conexão(context);
        banco = conexao.getWritableDatabase();

    }

    public long inserirConsulta(Consulta consulta){
        ContentValues values = new ContentValues();
        values.put("idpaciente", consulta.getIdpaciente());
        values.put("agendamento", consulta.getAgendamento());
        values.put("pago", consulta.getPago());
        return banco.insert("consulta", null,values);
    }

    public List<Consulta> obterConsultas(){
        List<Consulta> consultas = new ArrayList<>();
        /*Cursor cursor = banco.query("consulta", new String[]{"idconsulta","idpaciente","agendamento","pago" },
                null, null, null, null, null);*/

        Cursor cursor = banco.rawQuery("SELECT * FROM consulta INNER JOIN paciente ON consulta.idpaciente = paciente.cpf" +
                " ORDER BY consulta.agendamento DESC", null);


        while(cursor.moveToNext()){
            Consulta c1 = new Consulta();
            c1.setIdconsulta(cursor.getInt(0));
            c1.setIdpaciente(cursor.getString(1));
            c1.setAgendamento(cursor.getString(2));
            c1.setPago(cursor.getString(3));
            c1.setAnotacao(cursor.getString(4));
            c1.setNome(cursor.getString(6));
            c1.setNomepet(cursor.getString(9));
            c1.setRacapet(cursor.getString(10));
            c1.setIdadepet(cursor.getString(11));

            consultas.add(c1);
        }
        return consultas;

    }

    public void excluir(Consulta p){
        banco.delete("consulta", "idconsulta= ?", new String[]{p.getIdconsulta().toString()});
    }

    public void atualizar(Consulta consulta){
        ContentValues values = new ContentValues();
        values.put("idpaciente", consulta.getIdpaciente());
        values.put("agendamento", consulta.getAgendamento());
        values.put("pago", consulta.getPago());
        banco.update("consulta", values,
                "idconsulta = ?", new String[]{consulta.getIdconsulta().toString()});
    }

    public void inserirAnotacao(Consulta consulta){
        ContentValues values = new ContentValues();
        values.put("anotacao", consulta.getAnotacao());
        banco.update("consulta", values,
                "idconsulta = ?", new String[]{consulta.getIdconsulta().toString()});
    }


}
