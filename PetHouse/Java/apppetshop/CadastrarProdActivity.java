package com.example.apppetshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class CadastrarProdActivity extends AppCompatActivity {

    private EditText nome;
    private EditText preco;
    private EditText descricao;
    private ProdutoDAO dao;
    private Produto produto = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_prod);

        nome = findViewById(R.id.digitanomeprod);
        preco = findViewById(R.id.digitapreco);
        descricao = findViewById(R.id.digitaproddesc);
        dao = new ProdutoDAO(this);

        Intent it = getIntent();
        if(it.hasExtra("produto")){
            produto = (Produto) it.getSerializableExtra("produto");
            nome.setText(produto.getNome());
            preco.setText(produto.getPreco());
            descricao.setText(produto.getDescricao());

        }
    }

    public void CadastrarProduto(View view) {
            produto = new Produto();
            produto.setNome(nome.getText().toString());
            produto.setPreco(preco.getText().toString());
            produto.setDescricao(descricao.getText().toString());
            long codigo = dao.inserirProd(produto);
            Toast.makeText(this, "Produto inserido com codigo: " + codigo, Toast.LENGTH_SHORT).show();

    }

}