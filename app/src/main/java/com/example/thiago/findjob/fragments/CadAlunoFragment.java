package com.example.thiago.findjob.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Cargo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadAlunoFragment extends Fragment {

    private Spinner sp_cargos;
    private String[] cargos = new String[]{"Analista de Sistemas","Analista de Suporte"};
    private ArrayAdapter<Cargo> adp_cargos;

    public CadAlunoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cad_aluno, container, false);


        sp_cargos = (Spinner) view.findViewById(R.id.sp_cargos);
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this.getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                cargos);
        sp_cargos.setAdapter(spinnerArrayAdapter);

        sp_cargos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        return view;
    }
}
