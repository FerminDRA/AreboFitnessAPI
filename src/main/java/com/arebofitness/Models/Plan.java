/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "planes")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plan")
    private int id_plan;
    @Column(name = "nombre")
    private String name;
    @Column(name = "duracion")
    private String duration;
    @Column(name = "costo")
    private BigDecimal cost;
    @Column(name = "maxmembers")
    private int maxMember;
    @Column(name = "days")
    private int days;
    
    
}
