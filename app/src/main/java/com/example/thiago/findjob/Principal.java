package com.example.thiago.findjob;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.mikepenz.materialdrawer.AccountHeader;
import com.mikepenz.materialdrawer.AccountHeaderBuilder;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.interfaces.OnCheckedChangeListener;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.ProfileDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.SectionDrawerItem;
import com.mikepenz.materialdrawer.model.SwitchDrawerItem;
import com.mikepenz.materialdrawer.model.ToggleDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IProfile;


public class Principal extends ActionBarActivity {
    private Drawer navigationDrawerLeft;
    private AccountHeader headerNavigationLeft;
    private Toolbar mToolBar;
    private Toolbar mToolBarBotton;
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

        mToolBar = (Toolbar)findViewById(R.id.tb_main);
        mToolBar.setTitle("Find Job");
        mToolBar.setSubtitle("Just a subtitle");
        setSupportActionBar(mToolBar);

        headerNavigationLeft = new AccountHeaderBuilder()
                .withActivity(this)
                .withCompactStyle(false)
                .withSavedInstance(savedInstanceState)
                .withHeaderBackground(R.drawable.abc_action_bar_item_background_material)
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
                        new SecondaryDrawerItem().withName("Meus Processos").withIcon(getResources().getDrawable(R.drawable.eye)),
                        new SwitchDrawerItem().withName("SwitchDrawer").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener),
                        new ToggleDrawerItem().withName("ToggleDrawer").withChecked(true).withOnCheckedChangeListener(mOnCheckedChangeListener)
                )
                .withSavedInstance(savedInstanceState)
                .withSelectedItem(0)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int i, IDrawerItem iDrawerItem) {
                        switch (i) {
                            case 1:
                                Toast.makeText(Principal.this, "Botão" + i, Toast.LENGTH_SHORT).show();
                                break;
                            case 2:
                                Toast.makeText(Principal.this, "Botão" + i, Toast.LENGTH_SHORT).show();
                                break;
                            case 3:
                                Toast.makeText(Principal.this, "Botão" + i, Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                })
                .withOnDrawerItemLongClickListener(new Drawer.OnDrawerItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(View view, int i, IDrawerItem iDrawerItem) {
                        Toast.makeText(Principal.this, "onItemLongClick" + i, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                })
                .build();




        //ADD ITENS


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
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
