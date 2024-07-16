package com.sistemaelite.portatilplus_2.network;

public class Accesorio {
    public Accesorio(int idAccesorio, int numeroAccesorio, String nombreAccesorio, String estado) {
        this.idAccesorio = idAccesorio;
        this.numeroAccesorio = numeroAccesorio;
        this.nombreAccesorio = nombreAccesorio;
        this.estado = estado;
    }

    private int idAccesorio;
    private int numeroAccesorio;
    private String nombreAccesorio;
    private String estado;

    public Accesorio() {

    }

    // Getters y setters
    public int getIdAccesorio() {
        return idAccesorio;
    }

    public void setIdAccesorio(int idAccesorio) {
        this.idAccesorio = idAccesorio;
    }

    public int getNumeroAccesorio() {
        return numeroAccesorio;
    }

    public void setNumeroAccesorio(int numeroAccesorio) {
        this.numeroAccesorio = numeroAccesorio;
    }

    public String getNombreAccesorio() {
        return nombreAccesorio;
    }

    public void setNombreAccesorio(String nombreAccesorio) {
        this.nombreAccesorio = nombreAccesorio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
