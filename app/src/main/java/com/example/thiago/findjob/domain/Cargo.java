package com.example.thiago.findjob.domain;

import java.util.ArrayList;

/**
 * Created by THIAGO on 28/09/2015.
 */
public class Cargo {
    private int id;
    private String Nome;
    private ArrayList<Aluno> alunos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public ArrayList<Aluno> getAlunos() {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) {
        this.alunos = alunos;
    }
}
