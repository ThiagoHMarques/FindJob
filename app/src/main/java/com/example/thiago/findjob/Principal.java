package com.example.thiago.findjob;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.example.thiago.findjob.fragments.DadosAlunoFragment;
import com.example.thiago.findjob.fragments.EmpresasFragment;
import com.example.thiago.findjob.fragments.MeusProcessoFragment;
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


public class Principal extends ActionBarActivity {
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;
    private Toolbar mToolBar;
    private FragmentManager fm = getSupportFragmentManager();

    private OnCheckedChangeListener mOnCheckedChangeListener = new OnCheckedChangeListener(){
        @Override
        public void onCheckedChanged(IDrawerItem iDrawerItem, CompoundButton compoundButton, boolean b) {
            Toast.makeText(Principal.this,"onCheckedChanged"+(b ? "true" : "false"),Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        if(savedInstanceState == null){
            VagasFragment vagasFragment = new VagasFragment();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.content, vagasFragment, "vagasFragment");
            ft.commit();
        }


        mToolBar = (Toolbar)findViewById(R.id.tb_main);
        mToolBar.setTitle("Find Job");
        mToolBar.setSubtitle("Just a subtitle");
        setSupportActionBar(mToolBar);

        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withHeaderBackground(R.drawable.android)
                .withSavedInstance(savedInstanceState)
                .addProfiles(
                        new ProfileDrawerItem().withName("Thiago Marques").withEmail("thiagohsmarques@gmail.com")
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
                        new SecondaryDrawerItem().withName("Empresas").withIcon(getResources().getDrawable(R.drawable.briefcase)),
                        new SecondaryDrawerItem().withName("Dados cadastrais").withIcon(getResources().getDrawable(R.drawable.account)),
                        new SecondaryDrawerItem().withName("Meus Processos").withIcon(getResources().getDrawable(R.drawable.eye))
                )
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        if (i == 1) {
                            VagasFragment vagasFragment = new VagasFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, vagasFragment, "vagasFragment");
                            ft.commit();
                        }
                        if (i == 2) {
                            EmpresasFragment empresasFragment = new EmpresasFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, empresasFragment, "empresasFragment");
                            ft.commit();
                        }
                        if (i == 3) {
                            DadosAlunoFragment dadosAlunoFragment = new DadosAlunoFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, dadosAlunoFragment, "dadosAlunoFragment");
                            ft.commit();
                        }
                        if (i == 4) {
                            MeusProcessoFragment meusProcessoFragment = new MeusProcessoFragment();
                            FragmentTransaction ft = fm.beginTransaction();
                            ft.replace(R.id.content, meusProcessoFragment,"meusProcessoFragment");
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
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
