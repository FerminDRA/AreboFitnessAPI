/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.sql.Date;
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
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int id_pago;
    @Column(name = "fecha_inicio")
    private Date f_inicio;
    @Column(name = "fecha_fin")
    private Date f_fin;
    @Column(name = "comprobante")
    private String comprobante;
    @Column(name = "monto_pago")
    private float monto_pago;
    
    //FKs
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_usuario")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_admin")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Admin admin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_plan")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Plan plan;

    public Pago() {
    }

    public Pago(Date f_inicio, Date f_fin, String comprobante, float monto_pago, Usuario usuario, Admin admin, Plan plan) {
        this.f_inicio = f_inicio;
        this.f_fin = f_fin;
        this.comprobante = comprobante;
        this.monto_pago = monto_pago;
        this.usuario = usuario;
        this.admin = admin;
        this.plan = plan;
    }
    
    

    public int getId_pago() {
        return id_pago;
    }

    public void setId_pago(int id_pago) {
        this.id_pago = id_pago;
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

    public float getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(float monto_pago) {
        this.monto_pago = monto_pago;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    
    
}
