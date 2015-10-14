package com.example.thiago.findjob.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.activitys.PrincipalAluno;
import com.example.thiago.findjob.activitys.PrincipalEmpresa;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.domain.Pessoa;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;
import com.example.thiago.findjob.extras.SessionManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by THIAGO on 06/10/2015.
 */
public class LoginService {
    private Map<String,String> params;
    private String url = "http://findjob10.esy.es/index.php/Login/logar";
    private ProgressDialog pDialog;
    private Intent intent;


    public void login(Pessoa pessoa, final SessionManager sessionManager) {
        pDialog = new ProgressDialog(sessionManager.getContext());
        pDialog.setMessage("Por favor aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();
        params = new HashMap<String,String>();
        params.put("email",pessoa.getEmail().toString());
        params.put("senha", pessoa.getSenha().toString());
        CustomJsonObjectRequest jor = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                try{
                    if(jsonObject.getInt("tipousuario")==1){
                        Aluno aluno = new Aluno();
                        aluno.setNome(jsonObject.getString("nome"));
                        aluno.setIdade(jsonObject.getInt("idade"));
                        aluno.setIdAluno(jsonObject.getInt("pessoaAluno"));
                        aluno.setId(jsonObject.getInt("idpessoa"));
                        aluno.setEmail(jsonObject.getString("email"));
                        aluno.setTelefone(jsonObject.getString("telefone"));
                        aluno.setProfissao(jsonObject.getString("profissao"));
                        aluno.setEscolaridade(jsonObject.getString("escolaridade"));

                        Gson gson = new Gson();
                        String usuario = gson.toJson(aluno);
                        sessionManager.putUser(usuario);
                        sessionManager.putUserType("aluno");

                    }else{
                        Empresa empresa = new Empresa();
                        empresa.setNome(jsonObject.getString("nome"));
                        empresa.setId(jsonObject.getInt("idpessoa"));
                        empresa.setIdEmpresa(jsonObject.getInt("idempresa"));
                        empresa.setEmail(jsonObject.getString("email"));
                        empresa.setTelefone(jsonObject.getString("telefone"));
                        empresa.setCnpj(jsonObject.getString("cnpj"));
                        empresa.setRazaoSocial(jsonObject.getString("razaoSocial"));
                        empresa.setSegmento(jsonObject.getString("segmento"));

                        Gson gson = new Gson();
                        String usuario = gson.toJson(empresa);
                        sessionManager.putUser(usuario);
                        sessionManager.putUserType("empresa");
                    }
                    if(sessionManager.isLoggedIn()) {
                        if (sessionManager.getUserType().equals("aluno")) {
                            intent = new Intent(sessionManager.getContext(), PrincipalAluno.class);
                            sessionManager.getContext().startActivity(intent);
                        } else {
                            intent = new Intent(sessionManager.getContext(), PrincipalEmpresa.class);
                            sessionManager.getContext().startActivity(intent);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                pDialog.hide();
            }
        },new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast toast = Toast.makeText(sessionManager.getContext(), "Houve um erro ao efetuar login", Toast.LENGTH_LONG);
                toast.show();
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(jor,"login");
    }

}
