package com.example.thiago.findjob.activitys;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;

import com.example.thiago.findjob.R;
import com.example.thiago.findjob.extras.SessionManager;

public class SplashScreen extends ActionBarActivity{

    public static final int segundos=2;
    public static final int milisegundos=segundos*1000;
    public static final int delay=2;
    private ProgressBar progressBar;
    private SessionManager sessionManager;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setMax(maximo_progress());
        animacao();

    }

    public  void animacao(){
        new CountDownTimer(milisegundos,1000){

            @Override
            public void onTick(long l) {
                progressBar.setProgress(estabelecer_progresso(l));
            }

            @Override
            public void onFinish() {
                sessionManager = new SessionManager(SplashScreen.this);
                if(sessionManager.isLoggedIn()) {
                    if (sessionManager.getUserType().equals("aluno")) {
                        intent = new Intent(SplashScreen.this, PrincipalAluno.class);
                        startActivity(intent);
                        finish();
                    } else {
                        intent = new Intent(SplashScreen.this, PrincipalEmpresa.class);
                        startActivity(intent);
                        finish();
                    }
                }else{
                    intent = new Intent(SplashScreen.this,Login.class);
                    startActivity(intent);
                    finish();
                }

            }
        }.start();
    }

    public int estabelecer_progresso(long miliseconds){
        return (int)((milisegundos-miliseconds)/1000);
    }

    public int maximo_progress(){
        return segundos-delay;
    }
}

