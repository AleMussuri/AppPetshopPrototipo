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
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListarPacienteActivity extends AppCompatActivity {

    private android.widget.ListView ListView;
    private PacienteDAO dao;
    private List<Paciente> pacientes;
    private List<Paciente> pacientesFiltrados = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);

        ListView = findViewById(R.id.list_paciente);
        dao = new PacienteDAO(this);
        pacientes = dao.obterPacientes();
        pacientesFiltrados.addAll(pacientes);
        ArrayAdapter<Paciente> adaptador = new ArrayAdapter<Paciente>(this, android.R.layout.simple_expandable_list_item_1, pacientesFiltrados);
        ListView.setAdapter(adaptador);

        ListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getBaseContext(), "Item Clicado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ListarPacienteActivity.this, InfoPacienteActivity.class);
                intent.putExtra("nome", pacientes.get(i).getNome());
                intent.putExtra("endereco", pacientes.get(i).getEndereco());
                intent.putExtra("contato", pacientes.get(i).getContato());
                intent.putExtra("nomepet", pacientes.get(i).getNomepet());
                intent.putExtra("racapet", pacientes.get(i).getRacapet());
                intent.putExtra("idadepet", pacientes.get(i).getIdadepet());
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
                procurarPaciente(s);
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

    public void procurarPaciente(String nome){
        pacientesFiltrados.clear();
        for(Paciente p1: pacientes){
            if(p1.getNome().toLowerCase().contains(nome.toLowerCase())){
                pacientesFiltrados.add(p1);
            }
        }
        ListView.invalidateViews();

    }

    public void excluir(MenuItem item){
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Paciente pacienteExcluir = pacientesFiltrados.get(menuInfo.position);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Atenção")
                .setMessage("Realmente deseja excluir?")
                .setNegativeButton("NÃO", null)
                .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        pacientesFiltrados.remove(pacienteExcluir);
                        pacientes.remove(pacienteExcluir);
                        dao.excluir(pacienteExcluir);
                        ListView.invalidateViews();

                    }

                }).create();
        dialog.show();

    }
        public void cadastrar (MenuItem item){
            startActivity(new Intent(ListarPacienteActivity.this, CadastrarPacienteActivity.class));
        }

    public void atualizar (MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final Paciente pacienteAtualizar = pacientesFiltrados.get(menuInfo.position);
        startActivity(new Intent(ListarPacienteActivity.this, AtualizarPacienteActivity.class).putExtra("paciente", pacienteAtualizar));
    }

    @Override
    public void onResume(){
        super.onResume();
        pacientes = dao.obterPacientes();
        pacientesFiltrados.clear();
        pacientesFiltrados.addAll(pacientes);
        ListView.invalidateViews();
    }
}


