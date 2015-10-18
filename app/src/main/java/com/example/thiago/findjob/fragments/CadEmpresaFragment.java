package com.example.thiago.findjob.fragments;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.services.AlunoService;
import com.example.thiago.findjob.services.EmpresaService;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadEmpresaFragment extends Fragment {
    private EditText et_nome,et_segmento,et_email,et_senha,et_confsenha,et_telefone;
    private Button bt_salvar;

    public CadEmpresaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cad_empresa, container, false);

        et_nome = (EditText) view.findViewById(R.id.et_nome);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_telefone = (EditText) view.findViewById(R.id.et_telefone);
        et_segmento = (EditText) view.findViewById(R.id.et_segmento);
        et_confsenha = (EditText) view.findViewById(R.id.et_cadconfsenha);
        et_senha = (EditText) view.findViewById(R.id.et_cadsenha);
        bt_salvar = (Button) view.findViewById(R.id.salvar);

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean ok = true;
                if(et_telefone.getText().toString()==""){
                    ok=false;
                }
                if(et_nome.getText().toString()==""){
                    ok=false;
                }
                if(et_confsenha.getText().toString()==""){
                    ok=false;
                }
                if(et_segmento.getText().toString()==""){
                    ok=false;
                }
                if(et_senha.getText().toString()==""){
                    ok=false;
                }
                if(et_email.getText().toString()==""){
                    ok=false;
                }
                if(!ok){
                    Toast toast = Toast.makeText(getActivity(), "Preencha todos os dados", Toast.LENGTH_LONG);
                    toast.show();
                }else {
                    if (et_senha.getText().toString().equals(et_confsenha.getText().toString())) {
                        Empresa empresa = new Empresa();
                        EmpresaService empresaService = new EmpresaService();

                        empresa.setSegmento(et_segmento.getText().toString());
                        empresa.setEmail(et_email.getText().toString());
                        empresa.setNome(et_nome.getText().toString());
                        empresa.setSenha(et_senha.getText().toString());
                        empresa.setTelefone(et_telefone.getText().toString());

                        empresaService.inserir(empresa, getActivity());

                    } else {
                        Toast toast = Toast.makeText(getActivity(), "Senhas não conferem!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
            }
        });
        return view;
    }


}
