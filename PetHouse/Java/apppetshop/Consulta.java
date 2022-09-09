package com.example.apppetshop;

import java.io.Serializable;

public class Consulta extends Paciente implements Serializable  {

    private Integer idconsulta;
    private String idpaciente;
    private String agendamento;
    private String pago;
    private String anotacao;

    public Integer getIdconsulta() {
        return idconsulta;
    }

    public void setIdconsulta(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public String getAgendamento() {
        return agendamento;
    }

    public void setAgendamento(String agendamento) {
        this.agendamento = agendamento;
    }

    public String getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(String idpaciente) {
        this.idpaciente = idpaciente;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

    public String getAnotacao() { return anotacao; }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }


    @Override
    public  String toString(){
        return " Responsável: " + getNome() + "\n Pet: " + getNomepet()  + "\n Data Agendada: " + getAgendamento();
    }


}
