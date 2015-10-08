package com.example.thiago.findjob.domain;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.thiago.findjob.extras.SessionManager;
import com.example.thiago.findjob.services.LoginService;

/**
 * Created by THIAGO on 28/09/2015.
 */
public class Pessoa {
    private int id;
    private String nome;
    private String email;
    private String senha;
    private String telefone;
    private boolean status;

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void logar(SessionManager sessionManager){
        LoginService loginService = new LoginService();
        loginService.login(this,sessionManager);
    }


}
