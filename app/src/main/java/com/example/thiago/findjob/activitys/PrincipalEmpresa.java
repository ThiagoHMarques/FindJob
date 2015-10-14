package com.example.thiago.findjob.activitys;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.domain.Aluno;
import com.example.thiago.findjob.domain.Empresa;
import com.example.thiago.findjob.extras.SessionManager;
import com.example.thiago.findjob.fragments.AlunosFragment;
import com.example.thiago.findjob.fragments.CadastrarVagasFragment;
import com.example.thiago.findjob.fragments.DadosAlunoFragment;
import com.example.thiago.findjob.fragments.DadosEmpresaFragment;
import com.example.thiago.findjob.fragments.EmpresasFragment;
import com.example.thiago.findjob.fragments.MeusProcessoFragment;
import com.example.thiago.findjob.fragments.MinhasVagasFechadasFragment;
import com.example.thiago.findjob.fragments.MinhasVagasFragment;
import com.example.thiago.findjob.fragments.VagasFragment;
import com.google.gson.Gson;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class PrincipalEmpresa extends AppCompatActivity {
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;
    private Toolbar mToolBar;
    private FragmentManager fm = getSupportFragmentManager();
    private SessionManager sessionManager;
    private Intent intent;
    private Empresa empresaLogada;

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(PrincipalEmpresa.this, "onCheckedChanged" + (b ? "true" : "false"), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_empresa);
        if(Login.fa != null){
            Login.fa.finish();
        }
        sessionManager = new SessionManager(PrincipalEmpresa.this);
        empresaLogada = new Empresa();
        Gson gson = new Gson();
        String json = sessionManager.getUser();
        empresaLogada = gson.fromJson(json,Empresa.class);
        if(savedInstanceState == null){
            AlunosFragment alunosFragment = new AlunosFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, alunosFragment, "alunosFragment");
            ft.commit();
        }


        mToolBar = (Toolbar)findViewById(R.id.tb_main_empresa);
        mToolBar.setTitle("Find Job");
        mToolBar.setSubtitle("Just a subtitle");
        setSupportActionBar(mToolBar);

        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.account_header)
                .withSavedInstance(savedInstanceState)
                .addProfiles(
                        new ProfileDrawerItem().withName(empresaLogada.getNome().toString()).withEmail(empresaLogada.getEmail().toString())
                )
                .build();

        // NAVIGATION DRAWER
        navigationDrawerLeft = new DrawerBuilder()
                .withActivity(this)
                .withActionBarDrawerToggleAnimated(true)
                .withToolbar(mToolBar)
                .withDisplayBelowStatusBar(true)
                .withAccountHeader(headerNavigationLeft)
                .addDrawerItems(
                        new PrimaryDrawerItem().withName("Inicio"),
                        new SecondaryDrawerItem().withName("Minhas vagas abertas").withIcon(getResources().getDrawable(R.drawable.tooltip)),
                        new SecondaryDrawerItem().withName("Minhas vagas fechadas").withIcon(getResources().getDrawable(R.drawable.tooltip)),
                        new SecondaryDrawerItem().withName("Dados cadastrais").withIcon(getResources().getDrawable(R.drawable.account)),
                        new SecondaryDrawerItem().withName("Cadastrar vaga").withIcon(getResources().getDrawable(R.drawable.tooltipoutlineplus)),
                        new SecondaryDrawerItem().withName("Logout")
                )
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        if (i == 1) {
                            AlunosFragment alunosFragment = new AlunosFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, alunosFragment, "alunosFragment");
                            ft.commit();
                        }
                        if (i == 2) {
                            MinhasVagasFragment minhasVagasFragment = new MinhasVagasFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, minhasVagasFragment, "minhasVagasFragment");
                            ft.commit();
                        }
                        if (i == 3) {
                            MinhasVagasFechadasFragment minhasVagasFechadasFragment = new MinhasVagasFechadasFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, minhasVagasFechadasFragment, "minhasVagasFechadasFragment");
                            ft.commit();
                        }
                        if (i == 4) {
                            DadosEmpresaFragment dadosEmpresaFragment = new DadosEmpresaFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, dadosEmpresaFragment, "dadosEmpresaFragment");
                            ft.commit();
                        }
                        if (i == 5) {
                            CadastrarVagasFragment cadastrarVagasFragment = new CadastrarVagasFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, cadastrarVagasFragment, "cadastrarVagasFragment");
                            ft.commit();
                        }

                        if (i == 6) {
                            sessionManager.logout();
                            intent = new Intent(PrincipalEmpresa.this,Login.class);
                            startActivity(intent);
                            finish();
                        }


                        return false;
                    }
                })
                .build();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal_empresa, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Override this method in the activity that hosts the Fragment and call super
        // in order to receive the result inside onActivityResult from the fragment.


        if (resultCode == RESULT_OK) {
            // Get the Uri of the selected file
            Uri uri = data.getData();
            String id = DocumentsContract.getDocumentId(uri);
            Uri contentUri = ContentUris.withAppendedId(
                    Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
            File myFile = new File(getDataColumn(this, contentUri, null, null));
            byte[] bytes = new byte[0];
            try {
                bytes = loadFile(myFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String file = Base64.encodeToString(bytes, Base64.NO_WRAP);
            Log.d("file",file);
        }
    }

    private static byte[] loadFile(File file) throws IOException {
        InputStream is = new FileInputStream(file);

        long length = file.length();
        if (length > Integer.MAX_VALUE) {
            // File is too large
        }
        byte[] bytes = new byte[(int)length];

        int offset = 0;
        int numRead = 0;
        while (offset < bytes.length && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
            offset += numRead;
        }

        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }

        is.close();
        return bytes;
    }

    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
}
