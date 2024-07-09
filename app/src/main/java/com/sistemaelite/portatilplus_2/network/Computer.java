package com.sistemaelite.portatilplus_2.network;

public class Computer {
    private int idcomputador;



    public int getIdcomputador() {
        return idcomputador;
    }

    public void setIdcomputador(int idcomputador) {
        this.idcomputador = idcomputador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    private String marca;
    private String modelo;

    public Computer() {
        this.area = area;
        this.estado = estado;
        this.idcomputador = idcomputador;
        this.marca = marca;
        this.modelo = modelo;
    }

    private String estado;
    private String area;

    // Constructor, getters y setters
}