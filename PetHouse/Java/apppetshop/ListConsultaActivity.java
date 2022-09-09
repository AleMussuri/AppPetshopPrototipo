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

public class ListConsultaActivity extends AppCompatActivity {

    private android.widget.ListView ListView;
    private ConsultaDAO dao;
    private List<Consulta> consultas;
    private List<Consulta> consultasFiltradas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_consulta);

        ListView = findViewById(R.id.list_registros_consulta);
        dao = new ConsultaDAO(this);
        consultas = dao.obterConsultas();
        consultasFiltradas.addAll(consultas);
        ArrayAdapter<Consulta> adapt = new ArrayAdapter<Consulta>(this, android.R.layout.simple_expandable_list_item_1, consultasFiltradas);
        ListView.setAdapter(adapt);


        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "Item Clicado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListConsultaActivity.this, InfoConsultaActivity.class);
                intent.putExtra("nome", consultas.get(i).getNome());
                intent.putExtra("nomepet", consultas.get(i).getNomepet());
                intent.putExtra("racapet", consultas.get(i).getRacapet());
                intent.putExtra("idadepet", consultas.get(i).getIdadepet());
                intent.putExtra("agendamento", consultas.get(i).getAgendamento());
                intent.putExtra("inf", consultas.get(i).getAnotacao());

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
                System.out.println("Digitou" + s);
                return false;
            }
        });

        return true;
    }
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu,v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_consulta, menu);

    }

    public void procurarConsultas(String agendamento){
        consultasFiltradas.clear();
        for(Consulta p: consultas){
            if(p.getAgendamento().contains(agendamento)){
                consultasFiltradas.add(p);
            }
        }
        ListView.invalidateViews();

    }

    public void excluir(MenuItem consulta){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) consulta.getMenuInfo();
        final Consulta consExcluir = consultasFiltradas.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        consultasFiltradas.remove(consExcluir);
                        consultas.remove(consExcluir);
                        dao.excluir(consExcluir);
                        ListView.invalidateViews();

                    }

                }).create();
        dialog.show();

    }

    public void cadastrar (MenuItem item) {
        startActivity(new Intent(ListConsultaActivity.this, CadastrarConsultaActivity.class));
    }

    public void atualizar (MenuItem consulta){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) consulta.getMenuInfo();
        final Consulta consultaAtualizar = consultasFiltradas.get(menuInfo.position);
        startActivity(new Intent(ListConsultaActivity.this, AtualizarConsultaActivity.class).putExtra("consulta", consultaAtualizar));

    }

    public void inserirAnotacao (MenuItem consulta){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) consulta.getMenuInfo();
        final Consulta consultaAnotacao = consultasFiltradas.get(menuInfo.position);
        startActivity(new Intent(ListConsultaActivity.this, InserirAnotacaoActivity.class).putExtra("consulta", consultaAnotacao));

    }

    @Override
    public void onResume(){
        super.onResume();
        consultas = dao.obterConsultas();
        consultasFiltradas.clear();
        consultasFiltradas.addAll(consultas);
        ListView.invalidateViews();
    }
}