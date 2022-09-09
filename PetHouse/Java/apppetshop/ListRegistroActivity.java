package com.example.apppetshop;

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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class ListRegistroActivity extends AppCompatActivity {

    private android.widget.ListView ListView;
    private RegistroDAO dao;
    private List<Registro> registros;
    private List<Registro> registroFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_registro);

        ListView = findViewById(R.id.list_registros_consulta);
        dao = new RegistroDAO(this);
        registros = dao.obterRegistros();
        registroFiltradas.addAll(registros);
        ArrayAdapter<Registro> adapt = new ArrayAdapter<Registro>(this, android.R.layout.simple_expandable_list_item_1, registroFiltradas);
        ListView.setAdapter(adapt);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "Item Clicado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListRegistroActivity.this, InfoRegistroActivity.class);
                intent.putExtra("inf", registros.get(i).getInf());

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
                procurarConsultas(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                System.out.println("Digitou " + s);
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

    public void procurarConsultas(String agendamento){
        registroFiltradas.clear();
        for(Registro r: registros){
            if(r.getAgendamento().contains(agendamento)){
                registroFiltradas.add(r);
            }
        }
        ListView.invalidateViews();

    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Registro regisExcluir = registroFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        registroFiltradas.remove(regisExcluir);
                        registros.remove(regisExcluir);
                        dao.excluir(regisExcluir);
                        ListView.invalidateViews();

                    }

                }).create();
        dialog.show();

    }

    public void cadastrar (MenuItem item) {
        startActivity(new Intent(ListRegistroActivity.this, CadastrarRegistroActivity.class));
    }

    public void atualizar (MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Registro registroAtualizar = registroFiltradas.get(menuInfo.position);
        startActivity(new Intent(ListRegistroActivity.this, AtualizarRegistroActivity.class).putExtra("consulta", registroAtualizar));

    }

    @Override
    public void onResume(){
        super.onResume();
        registros = dao.obterRegistros();
        registroFiltradas.clear();
        registroFiltradas.addAll(registros);
        ListView.invalidateViews();
    }
}