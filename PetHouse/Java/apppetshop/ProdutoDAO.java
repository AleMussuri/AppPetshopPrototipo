package com.example.apppetshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    private Conexão conexao;
    private SQLiteDatabase banco;

    public ProdutoDAO(Context context){
        conexao = new Conexão(context);
        banco = conexao.getWritableDatabase();

    }

    public long inserirProd(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("descricao", produto.getDescricao());
        return banco.insert("produto", null, values);
    }

    public List<Produto> obterProdutos(){
        List<Produto> produtos = new ArrayList<>();
        Cursor cursor = banco.query("produto", new String[]{"codigo","nome", "preco", "descricao" },
                null, null, null, null, null);

        while(cursor.moveToNext()){
            Produto p1 = new Produto();
            p1.setCodigo(cursor.getInt(0));
            p1.setNome(cursor.getString(1));
            p1.setPreco(cursor.getString(2));
            p1.setDescricao(cursor.getString(3));
            produtos.add(p1);
        }
        return produtos;

    }

    public void excluir(Produto p){
        banco.delete("produto", "codigo= ?", new String[]{p.getCodigo().toString()});
    }

    public void atualizar(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("descricao", produto.getDescricao());
        banco.update("produto", values,
                "codigo = ?", new String[]{produto.getCodigo().toString()});
    }


}
