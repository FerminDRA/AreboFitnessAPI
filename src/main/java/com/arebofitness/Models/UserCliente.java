/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import lombok.NoArgsConstructor;

/**
 *
 * @author zarcorp
 */
@NoArgsConstructor()
@Entity
@DiscriminatorValue("Cliente")
public class UserCliente extends User{

    public UserCliente(String name, String lastname, String phone, String age, String email, String foto, String qr) {
        super(name, lastname, phone, age, email, foto, qr);
    }

    public void createCredentials() {
        super.createCredentials("Cliente");
    }
    
}
