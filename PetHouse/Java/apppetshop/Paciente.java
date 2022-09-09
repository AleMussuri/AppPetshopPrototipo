package com.example.apppetshop;

import java.io.Serializable;

public class Paciente implements Serializable {

    private String cpf;
    private String nome;
    private String endereco;
    private String contato;
    private String nomepet;
    private String racapet;
    private String idadepet;


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getNomepet() {
        return nomepet;
    }

    public void setNomepet(String nomepet) {
        this.nomepet = nomepet;
    }

    public String getRacapet() {
        return racapet;
    }

    public void setRacapet(String racapet) {
        this.racapet = racapet;
    }

    public String getIdadepet() {
        return idadepet;
    }

    public void setIdadepet(String idadepet) {
        this.idadepet = idadepet;
    }
    @Override
    public String toString(){ return "Nome do Pet: " + getNomepet() + " | CPF: " + getCpf() + "\nNome: " + getNome() + " | Telefone: " +getContato();
    }
}