package com.example.thiago.findjob.fragments;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Cargo;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadAlunoFragment extends Fragment {
    private Button bt_addCurriculo,bt_salvar;
    private Spinner sp_cargos;
    private String[] cargos = new String[]{"Analista de Sistemas","Analista de Suporte"};
    private ArrayAdapter<Cargo> adp_cargos;
    private String file;
    private Bundle bundle;
    private Intent intent = new Intent();

    public CadAlunoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cad_aluno, container, false);

        bt_addCurriculo = (Button) view.findViewById(R.id.bt_addCurriculo);
        bt_salvar = (Button) view.findViewById(R.id.salvar);

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

        bt_addCurriculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione o arquivo"), 1);
            }

        });


        bt_salvar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {


            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
