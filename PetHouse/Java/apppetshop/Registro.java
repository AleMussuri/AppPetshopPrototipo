package com.example.apppetshop;

import java.io.Serializable;

public class Registro extends Consulta implements Serializable {

    private Integer idregistro;
    private Integer idconsulta;
    private String inf;

    public Integer getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public Integer getIdconsulta() { return idconsulta;}

    public void setIdconsulta(Integer idconsulta) {
        this.idconsulta = idconsulta;
    }

    public String getInf() {
        return inf;
    }

    public void setInf(String inf) {
        this.inf = inf;
    }

    @Override
    public  String toString(){
        return "Numero do Registro: " + getIdconsulta();
    }

}
