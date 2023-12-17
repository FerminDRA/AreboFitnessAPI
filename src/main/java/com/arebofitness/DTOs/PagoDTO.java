/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@Data
@NoArgsConstructor
public class PagoDTO {
    private int id_pago;
    private String id_usuario;
    private ArrayList<String> usuarios;
    private int id_plan;
    private BigDecimal monto_pago;
    private Date f_inicio;
    private Date f_fin;
    private Timestamp fechapago;
    private String comprobante;

    public PagoDTO(String id_usuario, ArrayList<String> usuarios, int id_plan, BigDecimal id_costo, Date f_inicio, Date f_fin, Timestamp fechapago, String comprobante) {
        this.id_usuario = id_usuario;
        this.usuarios = usuarios;
        this.id_plan = id_plan;
        this.monto_pago = id_costo;
        this.f_inicio = f_inicio;
        this.f_fin = f_fin;
        this.fechapago = fechapago;
        this.comprobante = comprobante;
    }

    
}
