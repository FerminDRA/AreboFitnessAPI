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
public class UserDTO {
    private String id_usuario;
    private String nombres;
    private String apellidos;
    private String telefono;
    private String email;
    private String qr;
    private String foto;

    public UserDTO(String id_usuario, String nombres, String apellidos, String telefono, String email, String qr, String foto) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
        this.qr = qr;
        this.foto = foto;
    }
    
    

}
