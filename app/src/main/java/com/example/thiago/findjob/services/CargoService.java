package com.example.thiago.findjob.services;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.R;
import com.example.thiago.findjob.activitys.PrincipalAluno;
import com.example.thiago.findjob.activitys.PrincipalEmpresa;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Cargo;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.domain.Pessoa;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;
import com.example.thiago.findjob.extras.SessionManager;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by THIAGO on 16/10/2015.
 */
public class CargoService {

    private String url = "http://findjob10.esy.es/index.php/Cargo/listar_cargos";
    private ProgressDialog pDialog;


    public void getCargos(final List<Cargo> cargos, final Context context, final Spinner sp_cargos,final Cargo cargo, final Aluno aluno) {
        pDialog = new ProgressDialog(context);
        pDialog.setMessage("Por favor aguarde...");
        pDialog.setCancelable(false);
        pDialog.show();

        CustomJsonObjectRequest customJsonObjectRequest = new CustomJsonObjectRequest(Request.Method.POST, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                int index = 0;
                try {
                    if(aluno!=null){
                        cargo.setNome(aluno.getCargo().getNome());
                        cargo.setId(aluno.getCargo().getId());
                    }

                    JSONArray jsonCargos = jsonObject.getJSONArray("cargos");
                    for(int i=0; i < jsonCargos.length(); i++) {
                        Cargo cargoM = new Cargo();
                        JSONObject jsonCargo = jsonCargos.getJSONObject(i);
                        cargoM.setNome(jsonCargo.getString("desc"));
                        cargoM.setId(jsonCargo.getInt("idcargo"));
                        cargos.add(cargoM);
                        if(cargo.getNome()!=null) {
                            if (cargoM.getNome().equals(cargo.getNome())) {
                                index = i;
                            }
                        }
                    }

                    if(aluno==null){
                        cargo.setNome(cargos.get(0).getNome());
                        cargo.setId(cargos.get(0).getId());
                    }

                    String[] cargosString = new String[cargos.size()];
                    for(int i=0;cargos.size()>i;i++){
                        cargosString[i] = cargos.get(i).getNome();
                    }
                    ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context,
                            android.R.layout.simple_spinner_dropdown_item,
                            cargosString);
                    sp_cargos.setAdapter(spinnerArrayAdapter);
                    sp_cargos.setSelection(index);

                } catch (JSONException e) {
                    e.printStackTrace();
                    pDialog.hide();
                }

                pDialog.hide();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                pDialog.hide();
            }
        });
        AppController.getInstance().addToRequestQueue(customJsonObjectRequest);
    }
}
