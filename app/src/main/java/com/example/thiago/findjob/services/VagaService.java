package com.example.thiago.findjob.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.R;
import com.example.thiago.findjob.activitys.PrincipalAluno;
import com.example.thiago.findjob.adapters.MeusProcessosAdapter;
import com.example.thiago.findjob.adapters.MinhasVagasAdapter;
import com.example.thiago.findjob.adapters.MinhasVagasFechadasAdapter;
import com.example.thiago.findjob.adapters.VagaAdapter;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Cargo;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.domain.Vaga;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;
import com.example.thiago.findjob.extras.SessionManager;
import com.example.thiago.findjob.fragments.VagasFragment;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by THIAGO on 07/10/2015.
 */
public class VagaService {

    private Map<String,String> params;
    private List<Vaga> mVagas;
    private String url;
    private VagaAdapter vagaAdapter;
    private MeusProcessosAdapter meusProcessosAdapter;
    private MinhasVagasAdapter minhasVagasAdapter;
    private MinhasVagasFechadasAdapter minhasVagasFechadasAdapter;
    private ProgressDialog pDialog;
    private SessionManager sessionManager;

    public void getVagas(Aluno aluno, final Context context, final View view,final RecyclerView mRecyclerView,final String url, Empresa empresa){
        pDialog = new ProgressDialog(context);
        params = new HashMap<String,String>();
        mVagas = new ArrayList<Vaga>();


        pDialog.setMessage("Carregando vagas...");
        pDialog.setCancelable(false);
        pDialog.show();
        if(aluno != null){
            this.url = "http://findjob10.esy.es/index.php/Aluno_Vaga/"+url;
            params.put("idaluno", "" + aluno.getIdAluno());
        }if(empresa !=null){
            this.url = "http://findjob10.esy.es/index.php/Empresa/"+url;
            params.put("idempresa",""+empresa.getIdEmpresa());
        }
        CustomJsonObjectRequest jore = new CustomJsonObjectRequest(Request.Method.POST,this.url,params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray vagas = jsonObject.getJSONArray("vagas");
                    for(int i=0; i<vagas.length(); i++){
                        JSONObject jsonVaga = vagas.getJSONObject(i);

                        Vaga vaga = new Vaga();
                        Empresa empresa = new Empresa();
                        Cargo cargo = new Cargo();

                        cargo.setId(jsonVaga.getInt("idcargo"));
                        cargo.setNome(jsonVaga.getString("desc"));

                        empresa.setSegmento(jsonVaga.getString("segmento"));
                        empresa.setEmail(jsonVaga.getString("email"));
                        empresa.setNome(jsonVaga.getString("nome"));
                        empresa.setRazaoSocial(jsonVaga.getString("razaosocial"));
                        empresa.setCnpj(jsonVaga.getString("cnpj"));
                        empresa.setTelefone(jsonVaga.getString("telefone"));

                        vaga.setCargo(cargo);
                        vaga.setEmpresa(empresa);
                        vaga.setIdVaga(jsonVaga.getInt("idvaga"));
                        vaga.setDesc(jsonVaga.getString("descricao"));
                        vaga.setAnexo(jsonVaga.getString("anexo"));
                        vaga.setRemuneracao(jsonVaga.getString("remuneracao"));



                        mVagas.add(vaga);
                    }

                    if(url.equals("listar_vagas")) {
                        vagaAdapter = new VagaAdapter(context, mVagas);
                        mRecyclerView.setAdapter(vagaAdapter);
                    }if(url.equals("listar_meusprocessos")){
                        meusProcessosAdapter = new MeusProcessosAdapter(context, mVagas);
                        mRecyclerView.setAdapter(meusProcessosAdapter);
                    }if(url.equals("listar_vagasabertas")){
                        minhasVagasAdapter = new MinhasVagasAdapter(context, mVagas);
                        mRecyclerView.setAdapter(minhasVagasAdapter);
                    }if(url.equals("listar_vagasfechadas")){
                        minhasVagasFechadasAdapter = new MinhasVagasFechadasAdapter(context,mVagas);
                        mRecyclerView.setAdapter(minhasVagasFechadasAdapter);
                    }
                    pDialog.hide();

                } catch (JSONException e) {
                    pDialog.hide();
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(jore);
    }

    public void fecharVaga(final Context context, final View view,final Vaga vaga,final List<Vaga> mList, final MinhasVagasAdapter adapter){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();
        params = new HashMap<String,String>();
        params.put("idvaga", "" + vaga.getIdVaga());
        this.url = "http://findjob10.esy.es/index.php/Empresa/finalizar_vaga";
        CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                mList.remove(vaga);
                Toast toast = Toast.makeText(context, "Vaga fechada", Toast.LENGTH_LONG);
                adapter.notifyDataSetChanged();
                pDialog.hide();
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast toast = Toast.makeText(context, "Houve um erro ao fechar vaga", Toast.LENGTH_LONG);
                pDialog.hide();
                toast.show();
            }
        });

        AppController.getInstance().addToRequestQueue(customJsonObjectRequest);
    }

    public void abrirVaga(final Context context, final View view, final Vaga vaga,final List<Vaga> mList, final MinhasVagasFechadasAdapter adapter){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();
        params = new HashMap<String,String>();
        params.put("idvaga",""+vaga.getIdVaga());
        this.url = "http://findjob10.esy.es/index.php/Empresa/reabrir_vaga";
        CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                mList.remove(vaga);
                Toast toast = Toast.makeText(context, "Vaga reaberta", Toast.LENGTH_LONG);
                adapter.notifyDataSetChanged();
                pDialog.hide();
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast toast = Toast.makeText(context, "Houve um erro ao reabrir vaga", Toast.LENGTH_LONG);
                pDialog.hide();
                toast.show();
            }
        });
        AppController.getInstance().addToRequestQueue(customJsonObjectRequest);
    }

    public void candidatarVaga(final Context context, final View view, final Vaga vaga,final List<Vaga> mList, final VagaAdapter adapter){
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();
        params = new HashMap<String,String>();
        Aluno alunoLogado = new Aluno();
        sessionManager = new SessionManager(context);
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        alunoLogado = gson.fromJson(json,Aluno.class);

        url = "http://findjob10.esy.es/index.php/Aluno_Vaga/candidatar";

        params.put("idaluno",""+alunoLogado.getIdAluno());
        params.put("idvaga",""+vaga.getIdVaga());
        CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                mList.remove(vaga);
                Log.d("deu certo", "certo");
                Toast toast = Toast.makeText(context, "Voce se candidatou a vaga", Toast.LENGTH_LONG);
                adapter.notifyDataSetChanged();
                pDialog.hide();
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.d("deu errado","errado");
                Toast toast = Toast.makeText(context, "Houve um erro ao candidatar-se a vaga", Toast.LENGTH_LONG);
                pDialog.hide();
                toast.show();
            }
        });
        AppController.getInstance().addToRequestQueue(customJsonObjectRequest);
    }

    public void inserir(Vaga vaga, final Context context){
        pDialog = new ProgressDialog(context);
        url = "http://findjob10.esy.es/index.php/Empresa/cadastrar_vaga";
        pDialog.setMessage("Aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();
        Empresa empresaLogada = new Empresa();
        sessionManager = new SessionManager(context);
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        empresaLogada = gson.fromJson(json,Empresa.class);

        vaga.setEmpresa(empresaLogada);

        params = new HashMap<String,String>();
        params.put("descricao",vaga.getDesc());
        params.put("remuneracao",vaga.getRemuneracao());
        params.put("anexo",vaga.getAnexo());
        params.put("idempresa",""+vaga.getEmpresa().getIdEmpresa());
        params.put("idcargo",""+vaga.getCargo().getId());
        params.put("idtipovaga","1");

        CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Toast toast = Toast.makeText(context, "Vaga cadastrada com sucesso!", Toast.LENGTH_LONG);
                pDialog.hide();
                toast.show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast toast = Toast.makeText(context, "Houve um erro ao cadastrar a vaga!", Toast.LENGTH_LONG);
                pDialog.hide();
                toast.show();
            }
        });
        AppController.getInstance().addToRequestQueue(customJsonObjectRequest);
    }


}
