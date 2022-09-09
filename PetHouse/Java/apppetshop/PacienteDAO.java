package com.example.apppetshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    private Conexão conexao;
    private SQLiteDatabase banco;

    public PacienteDAO(Context context){
        conexao = new Conexão(context);
        banco = conexao.getWritableDatabase();

    }

    public long CadastrarPaciente(Paciente paciente){
        ContentValues values = new ContentValues();
        values.put("cpf", paciente.getCpf());
        values.put("nome", paciente.getNome());
        values.put("endereco", paciente.getEndereco());
        values.put("contato", paciente.getContato());
        values.put("nomepet", paciente.getNomepet());
        values.put("racapet", paciente.getRacapet());
        values.put("idadepet", paciente.getIdadepet());
        return banco.insert("paciente",null, values);
    }

    public List<Paciente> obterPacientes(){
        List<Paciente> paciente = new ArrayList<>();
        Cursor cursor = banco.query("paciente", new String[]{"cpf", "nome", "endereco", "contato", "nomepet", "racapet", "idadepet" },
                null, null, null, null, null);
        while(cursor.moveToNext()){
            Paciente p = new Paciente();
            p.setCpf(cursor.getString(0));
            p.setNome(cursor.getString(1));
            p.setEndereco(cursor.getString(2));
            p.setContato(cursor.getString(3));
            p.setNomepet(cursor.getString(4));
            p.setRacapet(cursor.getString(5));
            p.setIdadepet(cursor.getString(6));
            paciente.add(p);
        }
        return paciente;

    }

    public void excluir(Paciente p){
        banco.delete("paciente", "cpf= ?", new String[]{p.getCpf().toString()});
    }

    public void atualizar(Paciente paciente){
        ContentValues values = new ContentValues();
        values.put("cpf", paciente.getCpf());
        values.put("nome", paciente.getNome());
        values.put("endereco", paciente.getEndereco());
        values.put("contato", paciente.getContato());
        values.put("nomepet", paciente.getNomepet());
        values.put("racapet", paciente.getRacapet());
        values.put("idadepet", paciente.getIdadepet());
        banco.update("paciente", values,
                "cpf = ?", new String[]{paciente.getCpf().toString()});
    }



}
