package com.example.thiago.findjob.fragments;



import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Cargo;
import com.example.thiago.findjob.extras.FileManager;
import com.example.thiago.findjob.services.AlunoService;
import com.example.thiago.findjob.services.CargoService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadAlunoFragment extends Fragment {
    private Button bt_addCurriculo,bt_salvar;
    private EditText et_nome,et_email,et_idade,et_telefone,et_faculdade,et_senha,et_confsenha;
    private TextView et_anexo_desc;
    private Spinner sp_cargos;
    private String file;
    private Intent intent = new Intent();
    private FileManager fileManager = new FileManager();
    private CargoService cargoService = new CargoService();
    private List<Cargo> cargos = new ArrayList<Cargo>();
    private Cargo cargo = new Cargo();

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
        et_confsenha = (EditText) view.findViewById(R.id.et_cadconfsenha);
        et_senha = (EditText) view.findViewById(R.id.et_cadsenha);
        et_email = (EditText) view.findViewById(R.id.et_email);
        et_faculdade = (EditText) view.findViewById(R.id.et_faculdade);
        et_idade = (EditText) view.findViewById(R.id.et_idade);
        et_nome = (EditText) view.findViewById(R.id.et_nome);
        et_telefone = (EditText) view.findViewById(R.id.et_telefone);
        et_anexo_desc = (TextView) view.findViewById(R.id.vaga_anexo_desc);
        sp_cargos = (Spinner) view.findViewById(R.id.sp_cargos);

        cargoService.getCargos(cargos, getActivity(), sp_cargos, cargo, null);

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
                Boolean ok = true;
                if(et_telefone.getText().toString()==""){
                    ok=false;
                }if(et_nome.getText().toString()==""){
                    ok=false;
                }if(et_idade.getText().toString()==""){
                    ok=false;
                }if(et_faculdade.getText().toString()==""){
                    ok=false;
                }if(et_confsenha.getText().toString()==""){
                    ok=false;
                }if(et_email.getText().toString()==""){
                    ok=false;
                }if(et_senha.getText().toString()==""){
                    ok=false;
                }if(et_anexo_desc.getText().toString()==""){
                    ok=false;
                }
                if(!ok){
                    Toast toast = Toast.makeText(getActivity(), "Preencha todos os dados", Toast.LENGTH_LONG);
                    toast.show();
                }else{
                    if(et_senha.getText().toString().equals(et_confsenha.getText().toString())){
                        Aluno aluno = new Aluno();
                        AlunoService alunoService = new AlunoService();

                        aluno.setTelefone(et_telefone.getText().toString());
                        aluno.setEscolaridade(et_faculdade.getText().toString());
                        aluno.setIdade(Integer.parseInt(et_idade.getText().toString()));
                        aluno.setEmail(et_email.getText().toString());
                        aluno.setSenha(et_senha.getText().toString());
                        aluno.setNome(et_nome.getText().toString());
                        aluno.setCargo(cargo);
                        aluno.setAnexo(file);

                        alunoService.inserir(aluno,getActivity());

                    }else{
                        Toast toast = Toast.makeText(getActivity(),"Senhas não conferem!", Toast.LENGTH_LONG);
                        toast.show();
                    }
                }


            }
        });

        return view;
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
