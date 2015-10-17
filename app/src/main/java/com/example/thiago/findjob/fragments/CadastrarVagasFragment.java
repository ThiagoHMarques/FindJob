package com.example.thiago.findjob.fragments;


import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.example.thiago.findjob.domain.Cargo;
import com.example.thiago.findjob.domain.Vaga;
import com.example.thiago.findjob.extras.FileManager;
import com.example.thiago.findjob.services.CargoService;
import com.example.thiago.findjob.services.VagaService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarVagasFragment extends Fragment {
    private Button bt_adddetalhes,bt_salvar,bt_cancelar;
    private EditText et_remuneracao,et_descricao;
    private TextView et_anexo_desc;
    private Spinner sp_cargos;
    private List<Cargo> cargos = new ArrayList<Cargo>();
    private Cargo cargo;
    private String[] cargosSpinner = new String[]{};
    private String file;
    private Intent intent = new Intent();
    private FileManager fileManager = new FileManager();
    private Vaga vaga;
    private CargoService cargoService = new CargoService();
    private VagaService vagaService = new VagaService();

    public CadastrarVagasFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cadastrar_vagas, container, false);

        bt_adddetalhes = (Button) view.findViewById(R.id.bt_addDetalhesvaga);
        bt_cancelar = (Button) view.findViewById(R.id.bt_vaga_cancelar);
        bt_salvar = (Button) view.findViewById(R.id.bt_vaga_salvar);
        et_descricao = (EditText) view.findViewById(R.id.et_vaga_descricao);
        et_remuneracao = (EditText) view.findViewById(R.id.et_vaga_remuneracao);
        et_anexo_desc = (TextView) view.findViewById(R.id.vaga_anexo_desc);
        sp_cargos = (Spinner) view.findViewById(R.id.sp_cargos);

        cargo = new Cargo();
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

        bt_adddetalhes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent.setType("*/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecione o arquivo"), 1);
            }
        });

        bt_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_descricao.setText("");
                et_remuneracao.setText("");
                file = "";
                et_anexo_desc.setText("");
            }
        });

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean ok = true;
                vaga = new Vaga();
                if(cargo!=null){
                    vaga.setCargo(cargo);
                }else{
                    ok = false;
                }
                if(!et_remuneracao.getText().equals("")){
                    vaga.setRemuneracao(et_remuneracao.getText().toString());
                }else{
                    ok = false;
                }
                if(!et_descricao.getText().equals("")){
                    vaga.setDesc(et_descricao.getText().toString());
                }else{
                    ok=false;
                }
                if(!et_anexo_desc.getText().equals("")){
                    vaga.setAnexo(file);
                }else {
                    ok=false;
                }

                if(ok){
                    vagaService.inserir(vaga, getActivity());
                }else{
                    Toast toast = Toast.makeText(getActivity(), "Preencha todos os campos!", Toast.LENGTH_LONG);
                    toast.show();
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
            Log.d("file",file);
            et_anexo_desc.setText("Descrição da vaga anexada");
        }
    }



}
