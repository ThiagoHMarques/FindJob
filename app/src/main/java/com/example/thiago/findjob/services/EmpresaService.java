package com.example.thiago.findjob.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.adapters.EmpresaAdapter;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by THIAGO on 08/10/2015.
 */
public class EmpresaService {
    private String url;
    private List<Empresa> empresas;
    private ProgressDialog pDialog;

    public void getEmpresas(final Context context, final RecyclerView recyclerView, View view){
        url = "http://findjob10.esy.es/index.php/Empresa/listar_empresas";
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Carregando empresas...");
        pDialog.setCancelable(false);
        pDialog.show();

        CustomJsonObjectRequest jor = new CustomJsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                empresas = new ArrayList<Empresa>();
                try {
                    JSONArray jsonEmpresas = jsonObject.getJSONArray("empresas");
                    for(int i=0; i < jsonEmpresas.length(); i++) {
                        JSONObject jsonEmpresa = jsonEmpresas.getJSONObject(i);
                        Empresa empresa = new Empresa();

                        empresa.setCnpj(jsonEmpresa.getString("cnpj"));
                        empresa.setTelefone(jsonEmpresa.getString("telefone"));
                        empresa.setRazaoSocial(jsonEmpresa.getString("razaosocial"));
                        empresa.setSegmento(jsonEmpresa.getString("segmento"));
                        empresa.setNome(jsonEmpresa.getString("nome"));
                        empresa.setIdEmpresa(jsonEmpresa.getInt("idempresa"));
                        empresa.setId(jsonEmpresa.getInt("idpessoa"));
                        empresa.setEmail(jsonEmpresa.getString("email"));

                        empresas.add(empresa);
                    }

                    EmpresaAdapter empresaAdapter = new EmpresaAdapter(context,empresas);
                    recyclerView.setAdapter(empresaAdapter);

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
