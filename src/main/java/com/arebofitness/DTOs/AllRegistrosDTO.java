/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.sql.Time;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@NoArgsConstructor
@Data
@Entity
@Table(name = "allregisters")
public class AllRegistrosDTO {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_reg;
    private String id_usuario;
    private String nombres;
    private Time hora_entrada;
    private Time hora_salida;

    public AllRegistrosDTO(int id_reg, String id_usuario, String nombres, Time hora_entrada, Time hora_salida) {
        this.id_reg = id_reg;
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.hora_entrada = hora_entrada;
        this.hora_salida = hora_salida;
    }

    
}
