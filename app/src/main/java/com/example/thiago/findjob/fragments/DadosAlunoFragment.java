package com.example.thiago.findjob.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.thiago.findjob.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DadosAlunoFragment extends Fragment {


    public DadosAlunoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dados_aluno, container, false);
    }


}
