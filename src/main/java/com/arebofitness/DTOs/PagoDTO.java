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
public class PagoDTO {
    private int id_pago;
    private int id_usuario;
    private int id_admin;
    private int id_plan;
    private int id_costo;
    private Date f_inicio;
    private Date f_fin;
    private String comprobante;

    public PagoDTO(int id_usuario, int id_admin, int id_plan, int id_costo, Date f_inicio, Date f_fin, String comprobante) {
        this.id_usuario = id_usuario;
        this.id_admin = id_admin;
        this.id_plan = id_plan;
        this.id_costo = id_costo;
        this.f_inicio = f_inicio;
        this.f_fin = f_fin;
        this.comprobante = comprobante;
    }

    public PagoDTO(int id_pago, int id_usuario, int id_admin, int id_plan, int id_costo, Date f_inicio, Date f_fin, String comprobante) {
        this.id_pago = id_pago;
        this.id_usuario = id_usuario;
        this.id_admin = id_admin;
        this.id_plan = id_plan;
        this.id_costo = id_costo;
        this.f_inicio = f_inicio;
        this.f_fin = f_fin;
        this.comprobante = comprobante;
    }

    
    

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }
    
    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public int getId_costo() {
        return id_costo;
    }

    public void setId_costo(int id_costo) {
        this.id_costo = id_costo;
    }

    public Date getF_inicio() {
        return f_inicio;
    }

    public void setF_inicio(Date f_inicio) {
        this.f_inicio = f_inicio;
    }

    public Date getF_fin() {
        return f_fin;
    }

    public void setF_fin(Date f_fin) {
        this.f_fin = f_fin;
    }

    public String getComprobante() {
        return comprobante;
    }

    public void setComprobante(String comprobante) {
        this.comprobante = comprobante;
    }
    
    
}
