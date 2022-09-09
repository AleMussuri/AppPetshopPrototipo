package com.example.apppetshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AtualizarProdActivity extends AppCompatActivity {

    private EditText nome;
    private EditText preco;
    private EditText descricao;
    private ProdutoDAO dao;
    private Produto produto = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizar_prod);

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

    public void AtualizarProduto(View view) {
            produto.setNome(nome.getText().toString());
            produto.setPreco(preco.getText().toString());
            produto.setDescricao(descricao.getText().toString());
            dao.atualizar(produto);
            Toast.makeText(this, "Produto atualizado com sucesso!!", Toast.LENGTH_SHORT).show();
        }
    }

