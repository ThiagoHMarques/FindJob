package com.example.thiago.findjob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.extras.SessionManager;
import com.example.thiago.findjob.services.AlunoService;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class CandidatosFragment extends Fragment {
    private SessionManager sessionManager;
    private Empresa empresaLogada;

    public CandidatosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_candidatos, container, false);

        sessionManager = new SessionManager(getActivity());
        empresaLogada = new Empresa();
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        empresaLogada = gson.fromJson(json,Empresa.class);

        RecyclerView mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        AlunoService alunoService = new AlunoService();
        alunoService.candidatos(getActivity(), mRecyclerView, view, empresaLogada);

        return view;
    }


}
