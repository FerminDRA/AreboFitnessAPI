/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.math.BigDecimal;
import java.sql.Date;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@NoArgsConstructor
@Data
public class AllUsersDTO {
    private int id;
    private String id_usuario;
    private String nombres;
    private String telefono;
    private String nombreplan;
    private String duracion;
    private Date termino;
    private BigDecimal costo;

    
    public AllUsersDTO(int id,String id_usuario, String nombres, String telefono, String nombreplan, String periodo, Date termino, BigDecimal costo) {
        this.id=id;
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.telefono = telefono;
        this.nombreplan = nombreplan;
        this.duracion = periodo;
        this.termino = termino;
        this.costo = costo;
    }
    
    
}
