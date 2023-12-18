/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.persistence.Lob;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@Data
@NoArgsConstructor
public class AllPagosDTO {
//    private String id_user;
//    private String nombre;
//    private String nm_plan;
//    private String duracion;
//    private Timestamp fechaPago;
//    private BigDecimal costo;
//
//    public AllPagosDTO(String id_user, String nombre, String nm_plan, String duracion, Timestamp fechaPago, BigDecimal costo) {
//        this.id_user = id_user;
//        this.nombre = nombre;
//        this.nm_plan = nm_plan;
//        this.duracion = duracion;
//        this.fechaPago = fechaPago;
//        this.costo = costo;
//    }
    
    //private ArrayList<String> usuarios;
    //private String id_user;
    private int id;
    private String nombre;
    private String nm_plan;
    private String duracion;
    private Date fechaPago;
    private BigDecimal costo;
    @Lob
    private String comprobante;

    public AllPagosDTO(int id, String nombre, String nm_plan, String duracion, Date fechaPago, BigDecimal costo,String comprobante) {
        this.id = id;
        this.nombre = nombre;
        this.nm_plan = nm_plan;
        this.duracion = duracion;
        this.fechaPago = fechaPago;
        this.costo = costo;
        this.comprobante=comprobante;
    }
}
