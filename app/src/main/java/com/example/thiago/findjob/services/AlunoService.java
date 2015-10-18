package com.example.thiago.findjob.services;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.activitys.PrincipalAluno;
import com.example.thiago.findjob.adapters.AlunoAdapter;
import com.example.thiago.findjob.adapters.CandidatoAdapter;
import com.example.thiago.findjob.adapters.EmpresaAdapter;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Cargo;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.domain.Vaga;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;
import com.example.thiago.findjob.extras.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.Principal;
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
    private List<Vaga> vagas;
    private ProgressDialog pDialog;
    private SessionManager sessionManager;

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
                        aluno.setAnexo(jsonAluno.getString("anexo"));
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

    public void candidatos(final Context context, final RecyclerView recyclerView, View view, Empresa empresa){
        url = "http://findjob10.esy.es/index.php/Empresa/listar_candidatos";
        pDialog = new ProgressDialog(context);
        params = new HashMap<String,String>();
        pDialog.setMessage("Carregando alunos...");
        pDialog.setCancelable(false);
        pDialog.show();
        params.put("idempresa", "" + empresa.getIdEmpresa());

        CustomJsonObjectRequest jor = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                alunos = new ArrayList<Aluno>();
                vagas = new ArrayList<Vaga>();
                try {
                    JSONArray jsonAlunos = jsonObject.getJSONArray("alunos");
                    for(int i=0; i < jsonAlunos.length(); i++) {
                        JSONObject jsonAluno = jsonAlunos.getJSONObject(i);
                        Aluno aluno = new Aluno();
                        Vaga vaga = new Vaga();
                        Cargo cargo = new Cargo();

                        aluno.setEscolaridade(jsonAluno.getString("escolaridade"));
                        aluno.setIdAluno(jsonAluno.getInt("idaluno"));
                        aluno.setId(jsonAluno.getInt("idpessoa"));
                        aluno.setIdade(jsonAluno.getInt("idade"));
                        aluno.setProfissao(jsonAluno.getString("profissao"));
                        aluno.setAnexo(jsonAluno.getString("anexo"));
                        aluno.setEmail(jsonAluno.getString("email"));
                        aluno.setNome(jsonAluno.getString("nome"));
                        aluno.setTelefone(jsonAluno.getString("telefone"));

                        cargo.setId(jsonAluno.getInt("idcargo"));
                        cargo.setNome(jsonAluno.getString("desc"));

                        vaga.setCargo(cargo);
                        vaga.setIdVaga(jsonAluno.getInt("idvaga"));
                        vaga.setDesc(jsonAluno.getString("descricao"));
                        vaga.setAnexo(jsonAluno.getString("anexo"));
                        vaga.setRemuneracao(jsonAluno.getString("remuneracao"));

                        vagas.add(vaga);
                        alunos.add(aluno);
                    }

                    CandidatoAdapter candidatoAdapter = new CandidatoAdapter(context,alunos,vagas);
                    recyclerView.setAdapter(candidatoAdapter);

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

    public void update(final Aluno aluno, final Context context, String senha, String file){
        url = "http://findjob10.esy.es/index.php/Aluno/update";
        pDialog = new ProgressDialog(context);
        params = new HashMap<String,String>();
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();

        params.put("nome", aluno.getNome());
        params.put("email", aluno.getEmail());
        params.put("telefone", aluno.getTelefone());
        params.put("idade", "" + aluno.getIdade());
        params.put("escolaridade", aluno.getEscolaridade());
        params.put("id", "" + aluno.getId());
        params.put("idaluno", "" + aluno.getIdAluno());
        params.put("idcargo", "" + aluno.getCargo().getId());

        if(senha!=null){
            params.put("senha",senha);
        }
        params.put("anexo", file);

        CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Gson gson = new Gson();
                String usuario = gson.toJson(aluno);
                sessionManager = new SessionManager(context);
                sessionManager.putUser(usuario);
                sessionManager.putUserType("aluno");
                PrincipalAluno principalAluno = new PrincipalAluno();
                Toast toast = Toast.makeText(context,"Dados atualizados!", Toast.LENGTH_LONG);
                toast.show();
                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast toast = Toast.makeText(context,"Houve um erro ao atualizar os dados!", Toast.LENGTH_LONG);
                toast.show();
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(customJsonObjectRequest);
    }
}
