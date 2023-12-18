/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
    @Data
@NoArgsConstructor
@Entity
@Table(name = "pagos")
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago")
    private int id_pago;
    @Column(name = "usuarios")
    private ArrayList<String> usuarios;
    @Column(name = "f_inicio")
    private Date f_inicio;
    @Column(name = "f_fin")
    private Date f_fin;
    @Column(name = "fecha_pago")
    private Date fechaPago;
    @Lob
    @Column(name = "comprobante")
    private String comprobante;
    @Column(name = "monto_pago")
    private BigDecimal monto_pago;
    
    //FKs
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private UserPersonal usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_plan")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private Plan plan;

    public Pago(ArrayList<String> usuarios, Date f_inicio, Date f_fin, Date fechaPago, String comprobante, BigDecimal monto_pago, UserPersonal usuario, Plan plan) {
        this.usuarios = usuarios;
        this.f_inicio = f_inicio;
        this.f_fin = f_fin;
        this.fechaPago = fechaPago;
        this.comprobante = comprobante;
        this.monto_pago = monto_pago;
        this.usuario = usuario;
        this.plan = plan;
    }
    
    
    
}
