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
public class GetUsuarioDTO {
    private String id_usuario;
    private String nombres;
    private String apellidos;
    private String edad;
    private String telefono;
    private String correo;
    private String plan;
    private String foto;
    private String qr;

    public GetUsuarioDTO(String id_usuario, String nombres, String apellidos, String edad, String telefono, String correo, String plan, String foto, String qr) {
        this.id_usuario = id_usuario;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.plan = plan;
        this.foto= foto;
        this.qr= qr;
    }
    
}
