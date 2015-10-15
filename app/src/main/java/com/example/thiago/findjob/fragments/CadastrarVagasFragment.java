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
import android.widget.Button;
import android.widget.EditText;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.extras.FileManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * A simple {@link Fragment} subclass.
 */
public class CadastrarVagasFragment extends Fragment {
    private Button bt_adddetalhes;
    private EditText et_remuneracao,et_descricao;
    private Intent intent = new Intent();
    private FileManager fileManager = new FileManager();

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
            String file = Base64.encodeToString(bytes, Base64.NO_WRAP);
            Log.d("file", file);
        }
    }



}
