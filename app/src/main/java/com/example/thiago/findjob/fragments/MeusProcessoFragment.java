package com.example.thiago.findjob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Vaga;
import com.example.thiago.findjob.extras.SessionManager;
import com.example.thiago.findjob.services.VagaService;
import com.google.gson.Gson;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeusProcessoFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private List<Vaga> vagas;
    private VagaService vagaService;
    private SessionManager sessionManager;

    public MeusProcessoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meus_processo, container, false);

        Aluno alunoLogado = new Aluno();
        sessionManager = new SessionManager(getActivity());
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        alunoLogado = gson.fromJson(json,Aluno.class);

        vagaService = new VagaService();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);

        vagaService.getVagas(alunoLogado,getActivity(),view,mRecyclerView,"listar_meusprocessos",null);

        return view;
    }


}
