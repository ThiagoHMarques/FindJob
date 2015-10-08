package com.example.thiago.findjob.activitys;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.R;

import com.example.thiago.findjob.domain.Pessoa;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;
import com.example.thiago.findjob.extras.SessionManager;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login extends ActionBarActivity {
    public static Activity fa;
    private Intent intent;
    private Map<String,String> params;
    private ProgressDialog pDialog;
    private SessionManager sessionManager;

    private EditText et_usuario,et_senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        fa = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        sessionManager = new SessionManager(Login.this);

        et_usuario = (EditText) findViewById(R.id.etUsuario);
        et_senha = (EditText) findViewById(R.id.etSenha);
        params = new HashMap<String, String>();




    }

    public void onClickBtnEntrar(View v) throws InterruptedException {
        Pessoa pessoa = new Pessoa();
        pessoa.setEmail(et_usuario.getText().toString());
        pessoa.setSenha(et_senha.getText().toString());
        pessoa.logar(sessionManager);
    }

    public void onClickTvCadastrar(View v){
        intent = new Intent(Login.this,Cadastrar.class);
        startActivity(intent);
    }
}
