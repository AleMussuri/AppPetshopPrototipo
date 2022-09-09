package com.example.apppetshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListProdActivity extends AppCompatActivity {

    private android.widget.ListView ListView;
    private ProdutoDAO dao;
    private List<Produto> produtos;
    private List<Produto> produtosFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_prod);

        ListView = findViewById(R.id.list_prod);
        dao = new ProdutoDAO(this);
        produtos = dao.obterProdutos();
        produtosFiltrados.addAll(produtos);
        ArrayAdapter<Produto> adapt = new ArrayAdapter<Produto>(this, android.R.layout.simple_expandable_list_item_1, produtosFiltrados);
        ListView.setAdapter(adapt);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "Item Clicado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListProdActivity.this, InfoProdActivity.class);
                intent.putExtra("nome", produtos.get(i).getNome());
                intent.putExtra("codigo", produtos.get(i).getCodigo());
                intent.putExtra("preco", produtos.get(i).getPreco());
                intent.putExtra("descricao", produtos.get(i).getDescricao());
                startActivity(intent);
            }
        });

        registerForContextMenu(ListView);
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_lista, menu);
        SearchView sv = (SearchView) menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                procurarProdutos(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println("Digitou" + s);
                return false;
            }
        });

        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_item, menu);

    }

    public void procurarProdutos(String nome){
        produtosFiltrados.clear();
        for(Produto p: produtos){
            if(p.getDescricao().toLowerCase().contains(nome.toLowerCase())){
                produtosFiltrados.add(p);
            }
        }
        ListView.invalidateViews();

    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Produto prodExcluir = produtosFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        produtosFiltrados.remove(prodExcluir);
                        produtos.remove(prodExcluir);
                        dao.excluir(prodExcluir);
                        ListView.invalidateViews();

                    }

                }).create();
        dialog.show();

    }

    public void cadastrar (MenuItem item) {
        startActivity(new Intent(ListProdActivity.this, CadastrarProdActivity.class));
    }

    public void atualizar (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Produto prodAtualizar = produtosFiltrados.get(menuInfo.position);
        startActivity(new Intent(ListProdActivity.this, AtualizarProdActivity.class).putExtra("produto", prodAtualizar));

    }

    @Override
    public void onResume(){
        super.onResume();
        produtos = dao.obterProdutos();
        produtosFiltrados.clear();
        produtosFiltrados.addAll(produtos);
        ListView.invalidateViews();
    }
}