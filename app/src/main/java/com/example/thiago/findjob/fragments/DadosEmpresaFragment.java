package com.example.thiago.findjob.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.extras.SessionManager;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class DadosEmpresaFragment extends Fragment {
    private FloatingActionButton bt_edit;
    private Button bt_salvar,bt_cancelar;
    private EditText et_nome,et_email,et_telefone,et_segmento,et_senha,et_confsenha;
    private TextInputLayout til_senha,til_confsenha;
    private SessionManager sessionManager;
    private Empresa empresaLogada;

    public DadosEmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_empresa, container, false);

        bt_edit = (FloatingActionButton) view.findViewById(R.id.fab_edit);
        bt_cancelar = (Button) view.findViewById(R.id.empresa_cancelar);
        bt_salvar = (Button) view.findViewById(R.id.empresa_salvar);
        et_email = (EditText) view.findViewById(R.id.et_empresa_email);
        et_nome = (EditText) view.findViewById(R.id.et_empresa_nome);
        et_segmento = (EditText) view.findViewById(R.id.et_empresa_segmento);
        et_telefone = (EditText) view.findViewById(R.id.et_empresa_telefone);
        et_senha = (EditText) view.findViewById(R.id.et_empresa_senha);
        et_confsenha = (EditText) view.findViewById(R.id.et_empresa_confsenha);
        til_senha = (TextInputLayout) view.findViewById(R.id.et_senhaLayout);
        til_confsenha = (TextInputLayout) view.findViewById(R.id.et_confsenhaLayout);


        create();


        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bt_cancelar.setVisibility(View.VISIBLE);
                bt_salvar.setVisibility(View.VISIBLE);
                bt_edit.setVisibility(View.INVISIBLE);
                til_confsenha.setVisibility(View.VISIBLE);
                til_senha.setVisibility(View.VISIBLE);
                et_confsenha.setVisibility(View.VISIBLE);
                et_senha.setVisibility(View.VISIBLE);
                et_email.setEnabled(true);
                et_telefone.setEnabled(true);
                et_segmento.setEnabled(true);
                et_nome.setEnabled(true);
            }
        });

        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });



        return view;
    }

    public void create(){
        sessionManager = new SessionManager(getActivity());
        empresaLogada = new Empresa();
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        empresaLogada = gson.fromJson(json,Empresa.class);

        et_nome.setText(empresaLogada.getNome().toString());
        et_telefone.setText(empresaLogada.getTelefone().toString());
        et_segmento.setText(empresaLogada.getSegmento().toString());
        et_email.setText(empresaLogada.getEmail().toString());

        bt_cancelar.setVisibility(View.INVISIBLE);
        bt_salvar.setVisibility(View.INVISIBLE);
        bt_edit.setVisibility(View.VISIBLE);
        til_confsenha.setVisibility(View.INVISIBLE);
        til_senha.setVisibility(View.INVISIBLE);
        et_confsenha.setVisibility(View.INVISIBLE);
        et_senha.setVisibility(View.INVISIBLE);
        et_email.setEnabled(false);
        et_telefone.setEnabled(false);
        et_segmento.setEnabled(false);
        et_nome.setEnabled(false);

    }


}
