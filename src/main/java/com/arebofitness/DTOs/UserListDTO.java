/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@NoArgsConstructor
@Data
public class UserListDTO {
    private String nombre;
    private String apellidos;
    private String edad;
    private String telefono;
    private String correo;
    private String foto;
    private String qr;
    
}
