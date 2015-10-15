package com.example.thiago.findjob.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.extras.SessionManager;
import com.google.gson.Gson;


/**
 * A simple {@link Fragment} subclass.
 */
public class DadosAlunoFragment extends Fragment {
    private FloatingActionButton bt_editar;
    private Button bt_salvar,bt_cancelar;
    private TextView tv_nome,tv_email,tv_idade,tv_telefone,tv_faculdade,tv_senha,tv_confsenha;
    private TextInputLayout til_senha,til_confsenha;
    private SessionManager sessionManager;
    private Aluno alunoLogado;

    public DadosAlunoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_aluno, container, false);

        bt_editar = (FloatingActionButton) view.findViewById(R.id.fab_edit);
        tv_nome = (TextView) view.findViewById(R.id.et_aluno_nome);
        tv_email = (TextView) view.findViewById(R.id.et_aluno_email);
        tv_idade = (TextView) view.findViewById(R.id.et_aluno_idade);
        tv_senha = (TextView) view.findViewById(R.id.et_aluno_senha);
        tv_confsenha = (TextView) view.findViewById(R.id.et_aluno_confsenha);
        tv_faculdade = (TextView) view.findViewById(R.id.et_aluno_faculdade);
        tv_telefone = (TextView) view.findViewById(R.id.et_aluno_telefone);
        til_senha = (TextInputLayout) view.findViewById(R.id.et_senhaLayout);
        til_confsenha = (TextInputLayout) view.findViewById(R.id.et_confsenhaLayout);
        bt_salvar = (Button) view.findViewById(R.id.aluno_salvar);
        bt_cancelar = (Button) view.findViewById(R.id.aluno_cancelar);



        create();


        bt_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_nome.setEnabled(true);
                tv_email.setEnabled(true);
                tv_telefone.setEnabled(true);
                tv_idade.setEnabled(true);
                tv_senha.setVisibility(View.VISIBLE);
                tv_confsenha.setVisibility(View.VISIBLE);
                tv_senha.setEnabled(true);
                tv_confsenha.setEnabled(true);
                tv_telefone.setEnabled(true);
                tv_faculdade.setEnabled(true);
                til_confsenha.setVisibility(View.VISIBLE);
                til_senha.setVisibility(View.VISIBLE);
                bt_salvar.setVisibility(View.VISIBLE);
                bt_cancelar.setVisibility(View.VISIBLE);
                bt_editar.setVisibility(View.INVISIBLE);
            }
        });

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
        alunoLogado = new Aluno();
        sessionManager = new SessionManager(getActivity());
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        alunoLogado = gson.fromJson(json,Aluno.class);

        tv_nome.setText(alunoLogado.getNome().toString());
        tv_email.setText(alunoLogado.getEmail().toString());
        tv_telefone.setText(alunoLogado.getTelefone().toString());
        tv_idade.setText(""+alunoLogado.getIdade());
        tv_faculdade.setText(alunoLogado.getEscolaridade().toString());


        tv_nome.setEnabled(false);
        tv_email.setEnabled(false);
        tv_telefone.setEnabled(false);
        tv_idade.setEnabled(false);
        tv_senha.setVisibility(View.INVISIBLE);
        tv_confsenha.setVisibility(View.INVISIBLE);
        tv_telefone.setEnabled(false);
        tv_faculdade.setEnabled(false);
        til_confsenha.setVisibility(View.INVISIBLE);
        til_senha.setVisibility(View.INVISIBLE);
        bt_salvar.setVisibility(View.INVISIBLE);
        bt_cancelar.setVisibility(View.INVISIBLE);
        bt_editar.setVisibility(View.VISIBLE);
    }

}
