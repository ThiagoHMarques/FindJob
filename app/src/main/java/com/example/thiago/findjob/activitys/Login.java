package com.example.thiago.findjob.activitys;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.thiago.findjob.R;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends ActionBarActivity {
    private Intent intent;
    private RequestQueue rq;
    private Map<String,String> params;
    private String url = "www.google.com.br";
    private EditText et_usuario,et_senha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_usuario = (EditText) findViewById(R.id.etUsuario);
        et_senha = (EditText) findViewById(R.id.etSenha);

        params = new HashMap<String,String>();
        params.put("Usuario",et_usuario.getText().toString());
        params.put("Senha",et_senha.getText().toString());

        rq = Volley.newRequestQueue(Login.this);
    }

    public void onClickBtnEntrar(View v){
        CustomJsonObjectRequest jor = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(Login.this,"Error: "+ volleyError.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        jor.setTag("tag");
        rq.add(jor);

        intent = new Intent(Login.this,Principal.class);
        startActivity(intent);
        finish();
    }

    public void onClickTvCadastrar(View v){
        intent = new Intent(Login.this,Cadastrar.class);
        startActivity(intent);
    }

    @Override
    public void onStop(){
        super.onStop();
        rq.cancelAll("tag");
    }

}
