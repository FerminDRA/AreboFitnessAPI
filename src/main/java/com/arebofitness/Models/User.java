/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
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
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
public class User implements Serializable {
    
    @Id
    @Column(name = "user_id")
    private String id_usuario;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "age")
    private String age;
    @Column(name = "email")
    private String email;
    @Lob
    @Column(name = "foto")
    private String foto;
    @Lob
    @Column(name = "qr")
    private String qr;
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private CredentialsEntity credentials;
        

    public User(String name, String lastname, String phone, String age, String email, String foto, String qr) {
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.foto = foto;
        this.qr = qr;
        generateID();
    }
    
    public User(String id_usuario, String name, String lastname, String phone, String age, String email, String foto, String qr) {
        this.id_usuario=id_usuario;
        this.name = name;
        this.lastname = lastname;
        this.phone = phone;
        this.age = age;
        this.email = email;
        this.foto = foto;
        this.qr = qr;
    }

    public void createCredentials(String type) {
        CredentialsEntity c = new CredentialsEntity();
        c.setEmail(getEmail());
        c.setPassword(getPhone());
        c.setUserType(type);
        this.setCredentials(c);
        c.setUser(this);
    }

    private void generateID() {
        String firstNameInitials = this.name.substring(0, Math.min(this.name.length(), 2)).toUpperCase();
        String lastNameInitials = this.lastname.substring(0, Math.min(this.lastname.length(), 2)).toUpperCase();
        String randomNumbers = String.format("%02d", new Random().nextInt(100));
        String idCustom = "ID" + firstNameInitials + lastNameInitials + randomNumbers;
        this.id_usuario = idCustom;
    }
}
