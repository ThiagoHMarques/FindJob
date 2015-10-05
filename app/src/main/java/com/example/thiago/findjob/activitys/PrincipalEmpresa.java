package com.example.thiago.findjob.activitys;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.fragments.AlunosFragment;
import com.example.thiago.findjob.fragments.CadastrarVagasFragment;
import com.example.thiago.findjob.fragments.DadosAlunoFragment;
import com.example.thiago.findjob.fragments.DadosEmpresaFragment;
import com.example.thiago.findjob.fragments.EmpresasFragment;
import com.example.thiago.findjob.fragments.MeusProcessoFragment;
import com.example.thiago.findjob.fragments.MinhasVagasFragment;
import com.example.thiago.findjob.fragments.VagasFragment;
import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

public class PrincipalEmpresa extends AppCompatActivity {
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;
    private Toolbar mToolBar;
    private FragmentManager fm = getSupportFragmentManager();

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
                        new ProfileDrawerItem().withName("Thiago Marques Empresa").withEmail("thiagohsmarques@gmail.com")
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
                        new SecondaryDrawerItem().withName("Minhas vagas").withIcon(getResources().getDrawable(R.drawable.tooltip)),
                        new SecondaryDrawerItem().withName("Dados cadastrais").withIcon(getResources().getDrawable(R.drawable.account)),
                        new SecondaryDrawerItem().withName("Cadastrar vaga").withIcon(getResources().getDrawable(R.drawable.tooltipoutlineplus))
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
                            DadosEmpresaFragment dadosEmpresaFragment = new DadosEmpresaFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, dadosEmpresaFragment, "dadosEmpresaFragment");
                            ft.commit();
                        }
                        if (i == 4) {
                            CadastrarVagasFragment cadastrarVagasFragment = new CadastrarVagasFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, cadastrarVagasFragment, "cadastrarVagasFragment");
                            ft.commit();
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
}
