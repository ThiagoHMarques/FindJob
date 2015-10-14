package com.example.thiago.findjob.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.thiago.findjob.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarVagasFragment extends Fragment {
    private Button bt_adddetalhes;
    private EditText et_remuneracao,et_descricao;
    private Intent intent = new Intent();

    public CadastrarVagasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_vagas, container, false);

        bt_adddetalhes = (Button) view.findViewById(R.id.bt_addDetalhesvaga);
        et_descricao = (EditText) view.findViewById(R.id.bt_vaga_detalhes);
        et_remuneracao = (EditText) view.findViewById(R.id.et_vaga_remuneracao);

        bt_adddetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione o arquivo"), 1);
            }
        });

        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


}
