package com.example.thiago.findjob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.adapters.AlunoAdapter;
import com.example.thiago.findjob.adapters.MinhasVagasAdapter;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.extras.SessionManager;
import com.example.thiago.findjob.services.VagaService;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class MinhasVagasFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private SessionManager sessionManager;
    private VagaService vagaService;

    public MinhasVagasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_minhas_vagas, container, false);

        Empresa empresaLogado = new Empresa();
        sessionManager = new SessionManager(getActivity());
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        empresaLogado = gson.fromJson(json,Empresa.class);

        vagaService = new VagaService();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        vagaService.getVagas(null, getActivity(), view, mRecyclerView, "listar_vagasabertas", empresaLogado);

        return view;
    }


}
