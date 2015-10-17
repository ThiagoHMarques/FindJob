package com.example.thiago.findjob.fragments;


import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Cargo;
import com.example.thiago.findjob.extras.FileManager;
import com.example.thiago.findjob.extras.SessionManager;
import com.example.thiago.findjob.services.AlunoService;
import com.example.thiago.findjob.services.CargoService;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class DadosAlunoFragment extends Fragment {
    private FloatingActionButton bt_editar;
    private Button bt_salvar,bt_cancelar,bt_curriculo;
    private TextView tv_nome,tv_email,tv_idade,tv_telefone,tv_faculdade,tv_senha,tv_confsenha,et_anexo_desc;
    private TextInputLayout til_senha,til_confsenha;
    private SessionManager sessionManager;
    private Aluno alunoLogado;
    private CargoService cargoService = new CargoService();
    private Spinner sp_cargos;
    private Cargo cargo;
    private List<Cargo> cargos = new ArrayList<Cargo>();
    private FileManager fileManager = new FileManager();
    private String file = null;
    private Intent intent = new Intent();

    public DadosAlunoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_aluno, container, false);

        et_anexo_desc = (TextView) view.findViewById(R.id.vaga_anexo_desc);
        bt_editar = (FloatingActionButton) view.findViewById(R.id.fab_edit);
        tv_nome = (TextView) view.findViewById(R.id.et_aluno_nome);
        sp_cargos = (Spinner) view.findViewById(R.id.sp_cargos);
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
        bt_curriculo = (Button) view.findViewById(R.id.bt_edtAdicionarCurriculo);

        alunoLogado = new Aluno();
        sessionManager = new SessionManager(getActivity());
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        alunoLogado = gson.fromJson(json,Aluno.class);
        cargo = new Cargo();
        cargoService.getCargos(cargos, getActivity(), sp_cargos, cargo,alunoLogado);

        sp_cargos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (cargo != null) {
                    cargo.setNome(cargos.get(i).getNome());
                    cargo.setId(cargos.get(i).getId());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        create();


        bt_curriculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione o arquivo"), 1);
            }
        });

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
                sp_cargos.setEnabled(true);
                bt_curriculo.setEnabled(true);
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

                alunoLogado.setNome(tv_nome.getText().toString());
                alunoLogado.setEmail(tv_email.getText().toString());
                alunoLogado.setTelefone(tv_telefone.getText().toString());
                alunoLogado.setIdade(Integer.parseInt(tv_idade.getText().toString()));
                alunoLogado.setEscolaridade(tv_faculdade.getText().toString());
                alunoLogado.setCargo(cargo);

                if(tv_senha.getText().toString().equals("") && tv_confsenha.getText().toString().equals("")){
                    AlunoService alunoService = new AlunoService();
                    String senha = null;
                    alunoService.inserir(alunoLogado,getActivity(),senha,file);
                }else{
                    if(tv_senha.getText().toString().equals(tv_confsenha.getText().toString())){
                        AlunoService alunoService = new AlunoService();
                        String senha = tv_senha.getText().toString();
                        alunoService.inserir(alunoLogado,getActivity(),senha,file);
                    }else{
                        Toast toast = Toast.makeText(getActivity(),"Senhas não conferem!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }
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


        tv_nome.setText(alunoLogado.getNome().toString());
        tv_email.setText(alunoLogado.getEmail().toString());
        tv_telefone.setText(alunoLogado.getTelefone().toString());
        tv_idade.setText(""+alunoLogado.getIdade());
        tv_faculdade.setText(alunoLogado.getEscolaridade().toString());


        tv_nome.setEnabled(false);
        tv_email.setEnabled(false);
        tv_telefone.setEnabled(false);
        tv_idade.setEnabled(false);
        sp_cargos.setEnabled(false);
        tv_senha.setVisibility(View.INVISIBLE);
        tv_confsenha.setVisibility(View.INVISIBLE);
        tv_telefone.setEnabled(false);
        tv_faculdade.setEnabled(false);
        et_anexo_desc.setText("");
        file = null;
        til_confsenha.setVisibility(View.INVISIBLE);
        til_senha.setVisibility(View.INVISIBLE);
        bt_salvar.setVisibility(View.INVISIBLE);
        bt_cancelar.setVisibility(View.INVISIBLE);
        bt_editar.setVisibility(View.VISIBLE);
        bt_curriculo.setEnabled(false);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == getActivity().RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data.getData();
            String id = DocumentsContract.getDocumentId(uri);
            Uri contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            File myFile = new File(fileManager.getDataColumn(getActivity(), contentUri, null, null));
            byte[] bytes = new byte[0];
            try {
                bytes = fileManager.loadFile(myFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            file = Base64.encodeToString(bytes, Base64.NO_WRAP);
            Log.d("file", file);
            et_anexo_desc.setText("Curriculo anexado");
        }
    }
}
