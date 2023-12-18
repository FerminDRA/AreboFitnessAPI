/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@NoArgsConstructor
@Data
public class UsuarioPagoDTO {
    private List<UserListDTO> users;
    private String user_id;
    private int id_plan;
    private Date f_inicio;
    private Date f_fin;
    private Date fechaPago;
    private String comprobante;

}
