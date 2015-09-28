package com.example.thiago.findjob.domain;

import java.util.ArrayList;

/**
 * Created by THIAGO on 21/09/2015.
 */
public class Vaga {
    private String desc;
    private String cargo;
    private String remuneracao;
    private String anexo;
    private Empresa empresa;
    private ArrayList<Aluno> candidatos;

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ArrayList<Aluno> getCandidatos() {
        return candidatos;
    }

    public void setCandidatos(ArrayList<Aluno> candidatos) {
        this.candidatos = candidatos;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getRemuneracao() {
        return remuneracao;
    }

    public void setRemuneracao(String remuneracao) {
        this.remuneracao = remuneracao;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }
}
