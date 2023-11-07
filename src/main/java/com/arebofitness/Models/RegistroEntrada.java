/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author fermin
 */

@Entity
@Table(name = "registro_entradas")
public class RegistroEntrada {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_registros")
    private int id_registro;
    @Column(name = "hora_entrada")
    private Time h_entrada;
    @Column(name = "hora_salida")
    private Time h_salida;
    @Column(name = "fecha")
    private Date fecha;
    //FK
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuarios")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Usuario usuario;

    public RegistroEntrada() {
    }

    public RegistroEntrada(Time h_entrada, Date fecha, Usuario usuario) {
        this.h_entrada = h_entrada;
        this.fecha = fecha;
        this.usuario = usuario;
    }
    
    

    public int getId_registro() {
        return id_registro;
    }

    public void setId_registro(int id_registro) {
        this.id_registro = id_registro;
    }

    public Time getH_entrada() {
        return h_entrada;
    }

    public void setH_entrada(Time h_entrada) {
        this.h_entrada = h_entrada;
    }

    public Time getH_salida() {
        return h_salida;
    }

    public void setH_salida(Time h_salida) {
        this.h_salida = h_salida;
    }

    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
