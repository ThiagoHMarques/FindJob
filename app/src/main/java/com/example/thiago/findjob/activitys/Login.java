package com.example.thiago.findjob.activitys;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.thiago.findjob.R;

public class Login extends ActionBarActivity {
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onClickBtnEntrar(View v){
        intent = new Intent(Login.this,Principal.class);
        startActivity(intent);
        finish();
    }

    public void onClickTvCadastrar(View v){
        Toast toast = Toast.makeText(Login.this, "Cadastrar",Toast.LENGTH_LONG);
        toast.show();
    }

}
