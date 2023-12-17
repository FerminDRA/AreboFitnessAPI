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
    private String id;
    private String nombre;
    private String apellidos;
    private String edad;
    private String telefono;
    private String correo;
    private String foto;
    private String qr;
    private String nombreHr;

    public UserListDTO(String id,String nombre, String apellidos, String edad, String telefono, String correo, String foto, String qr, String nombreHr) {
        this.id=id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.foto = foto;
        this.qr = qr;
        this.nombreHr = nombreHr;
    }
    
    
}
