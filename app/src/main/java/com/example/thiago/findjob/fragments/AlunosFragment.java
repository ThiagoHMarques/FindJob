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
import com.example.thiago.findjob.adapters.VagaAdapter;
import com.example.thiago.findjob.services.AlunoService;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlunosFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public AlunosFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alunos, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_list);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(llm);


        AlunoService alunoService = new AlunoService();
        alunoService.getAlunos(getActivity(),mRecyclerView,view);

        return view;
    }


}
