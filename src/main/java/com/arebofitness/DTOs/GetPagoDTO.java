/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.sql.Date;
import javax.persistence.Column;

/**
 *
 * @author fermin
 */

//id pago- Numero de pago del usuario, nombre del admin, nombre del plan, periodo de susc, fechas, costo
public class GetPagoDTO {
    private int id_pago;
    private String nmAdmin;
    private String nmPlan;
    private String periodo;
    private Date f_inicio;
    private Date f_fin;
    private float costo;

    public GetPagoDTO(int id_pago, String nmAdmin, String nmPlan, String periodo, Date f_inicio, Date f_fin, float costo) {
        this.id_pago = id_pago;
        this.nmAdmin = nmAdmin;
        this.nmPlan = nmPlan;
        this.periodo = periodo;
        this.f_inicio = f_inicio;
        this.f_fin = f_fin;
        this.costo = costo;
    }

    
    
    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
    }

    public String getNmAdmin() {
        return nmAdmin;
    }

    public void setNmAdmin(String nmAdmin) {
        this.nmAdmin = nmAdmin;
    }

    public String getNmPlan() {
        return nmPlan;
    }

    public void setNmPlan(String nmPlan) {
        this.nmPlan = nmPlan;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
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

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }
    
    
}
