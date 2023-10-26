/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 *
 * @author fermin
 */
@Entity
@Table(name = "Usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuarios")
    private int id_usuario;
    @Column(name = "nombres")
    private String nombres;
    @Column(name = "apellidos")
    private String apellidos;
    @Column(name = "edad")
    private int edad;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "correo")
    private String correo;
    @Column(name = "foto_perfil")
    private byte[] foto_perfil;
    //FKs
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_tipo")
    //int id_tipo;
    private TipoUsuario usrTipe;
    //@Column(name = "id_plan")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_plan")
    //int id_plan;
    private Plan plan;
    //@Column(name = "id_horario")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_horario")
    //int id_horario;
    private Horario horario;

    public Usuario() {
    }
    
    

    public Usuario(String nombres, String apellidos, int edad, String telefono, String correo, byte[] foto_Perfil) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.foto_perfil = foto_Perfil;
    }

    public Usuario(String nombres, String apellidos, int edad, String telefono, String correo, byte[] foto_Perfil, TipoUsuario usrTipe, Plan plan) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.telefono = telefono;
        this.correo = correo;
        this.foto_perfil = foto_Perfil;
        this.usrTipe = usrTipe;
        this.plan = plan;
    }

    

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public byte[] getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(byte[] foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    

    public TipoUsuario getUsrTipe() {
        return usrTipe;
    }

    public void setUsrTipe(TipoUsuario usrTipe) {
        this.usrTipe = usrTipe;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    
}
