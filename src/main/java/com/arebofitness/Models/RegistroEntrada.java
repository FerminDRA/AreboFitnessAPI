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
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@NoArgsConstructor
@Data
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
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private UserCliente usuario;

    public RegistroEntrada(Time h_entrada, Date fecha, UserCliente usuario) {
        this.h_entrada = h_entrada;
        this.fecha = fecha;
        this.usuario = usuario;
    }

    
}
