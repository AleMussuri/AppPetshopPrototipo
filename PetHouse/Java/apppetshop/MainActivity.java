package com.example.apppetshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    EditText editLogin;
    EditText editPassword;
    String s;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLogin = findViewById(R.id.editLogin);
                editPassword = findViewById(R.id.editPassword);
                String login = editLogin.getText().toString();
                String senha = editPassword.getText().toString();
                if(login.equals("user")&&senha.equals("123")){
                    alert("Loguin realizado com sucesso");
                    startActivity(new Intent(MainActivity.this, MenuActivity.class));

                }else {
                    alert("Login ou senha incorreta");
                }
            }
        });
    }
    private void alert(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}

/*
public class MainActivity extends AppCompatActivity {

    String urlWebServices="http://192.168.0.10/apppetshop/getUsuarios.php";

    StringRequest stringRequest;
    RequestQueue requestQueue;

    Button btnLogin;
    EditText editLogin;
    EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        requestQueue = Volley.newRequestQueue(this);

        btnLogin = findViewById(R.id.btnLogin);
        editLogin = findViewById(R.id.editLogin);
        editPassword = findViewById(R.id.editPassword);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado = true;

                if(editLogin.getText().length()==0){
                    editLogin.setError("Campo obrigatório");
                    editLogin.requestFocus();
                    validado = false;
                }

                if(editPassword.getText().length()==0){
                    editPassword.setError("Campo obrigatório");
                    editPassword.requestFocus();
                    validado = false;
                }

                if(validado){
                    Toast.makeText(getApplicationContext(), "Validando dados... aguarde...", Toast.LENGTH_SHORT).show();

                    validarLogin();
                    CadastrarProduto cadastrarproduto = new CadastrarProduto();
                }
            }
        });
    }

    private void validarLogin() {
            stringRequest = new StringRequest(Request.Method.POST,
                    urlWebServices, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.v("LogLogin", response);

                    try {
                        JSONObject jsonObject = new JSONObject(response);

                        boolean isErro = jsonObject.getBoolean("erro");

                        if(isErro){
                            Toast.makeText(getApplicationContext(),jsonObject.getString("mensagem"),Toast.LENGTH_LONG).show();
                        }
                        else{
                            int funcao = jsonObject.getInt("funcao");

                            if(funcao==1){
                                Intent novaTela = new Intent(MainActivity.this,PainelAdmActivity.class);
                                startActivity(novaTela);
                                finish();
                            }else if(funcao==2){
                                Intent novaTela = new Intent(MainActivity.this,PainelUsuarioActivity.class);
                                startActivity(novaTela);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), "Usuário autenticado com sucesso!", Toast.LENGTH_LONG).show();
                        }

                    }catch (Exception e){
                        Log.v("LogLogin", e.getMessage());
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("LogLogin", error.getMessage());
                }
            })
            {
                @Override
                protected Map<String, String> getParams() {
                    Map<String, String> params = new HashMap<>();
                    params.put("login",editLogin.getText().toString());
                    params.put("senha",editPassword.getText().toString());
                    return params;
                }
            };
            requestQueue.add(stringRequest);
    }
} */