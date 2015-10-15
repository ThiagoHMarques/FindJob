package com.example.thiago.findjob.services;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.adapters.AlunoAdapter;
import com.example.thiago.findjob.adapters.EmpresaAdapter;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;
import com.example.thiago.findjob.extras.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by THIAGO on 08/10/2015.
 */
public class AlunoService {
    private String url;
    private Map<String,String> params;
    private List<Aluno> alunos;
    private ProgressDialog pDialog;

    public void getAlunos(final Context context, final RecyclerView recyclerView, View view){
        url = "http://findjob10.esy.es/index.php/Aluno/listar_todos";
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Carregando alunos...");
        pDialog.setCancelable(false);
        pDialog.show();

        CustomJsonObjectRequest jor = new CustomJsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                alunos = new ArrayList<Aluno>();
                try {
                    JSONArray jsonAlunos = jsonObject.getJSONArray("alunos");
                    for(int i=0; i < jsonAlunos.length(); i++) {
                        JSONObject jsonAluno = jsonAlunos.getJSONObject(i);
                        Aluno aluno = new Aluno();

                        aluno.setEscolaridade(jsonAluno.getString("escolaridade"));
                        aluno.setIdAluno(jsonAluno.getInt("idaluno"));
                        aluno.setId(jsonAluno.getInt("idpessoa"));
                        aluno.setIdade(jsonAluno.getInt("idade"));
                        aluno.setProfissao(jsonAluno.getString("profissao"));
                        aluno.setEmail(jsonAluno.getString("email"));
                        aluno.setNome(jsonAluno.getString("nome"));
                        aluno.setTelefone(jsonAluno.getString("telefone"));


                        alunos.add(aluno);
                    }

                    AlunoAdapter alunoAdapter = new AlunoAdapter(context,alunos);
                    recyclerView.setAdapter(alunoAdapter);

                    pDialog.hide();
                } catch (JSONException e) {
                    e.printStackTrace();
                    pDialog.hide();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(jor);

    }
}
