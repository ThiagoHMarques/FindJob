package com.example.thiago.findjob.services;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Vaga;
import com.example.thiago.findjob.extras.AppController;
import com.example.thiago.findjob.extras.CustomJsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by THIAGO on 07/10/2015.
 */
public class VagaService {
    private String url;
    private Map<String,String> params;
    public void getVagas(Aluno aluno,List<Vaga> vagas, Context context){
        url = "";
        params = new HashMap<String,String>();
        params.put("idAluno", "" + aluno.getIdAluno());
        CustomJsonObjectRequest jore = new CustomJsonObjectRequest(Request.Method.POST,url,params, new Response.Listener<JSONObject>(){
            @Override
            public void onResponse(JSONObject jsonObject) {
                try {
                    JSONArray vagas = jsonObject.getJSONArray("vagas");
                    for(int i=0; i<vagas.length(); i++){
                        JSONObject jsonVaga = vagas.getJSONObject(i);
                        Vaga vaga = new Vaga();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        AppController.getInstance().addToRequestQueue(jore);
    }
}
