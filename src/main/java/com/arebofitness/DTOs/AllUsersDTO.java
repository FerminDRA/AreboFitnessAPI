/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.sql.Date;

/**
 *
 * @author fermin
 */
public class AllUsersDTO {
    private int id_usuario;
    private String nombres;
    private int telefono;
    private String nombreplan;
    private String periodo;
    private Date termino;
    private int costo;

    
    public AllUsersDTO(int id_usuario, String nombres, int telefono, String nombreplan, String periodo, Date termino, int costo) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.telefono = telefono;
        this.nombreplan = nombreplan;
        this.periodo = periodo;
        this.termino = termino;
        this.costo = costo;
    }
    
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getNombreplan() {
        return nombreplan;
    }

    public void setNombreplan(String nombreplan) {
        this.nombreplan = nombreplan;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public Date getTermino() {
        return termino;
    }

    public void setTermino(Date termino) {
        this.termino = termino;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }
    
    
}
