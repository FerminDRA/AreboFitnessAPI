/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

/**
 *
 * @author zarcorp
 */
@NoArgsConstructor()
@Entity
@DiscriminatorValue("Cliente")
public class UserCliente extends User{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_plan")
    @JsonBackReference
    private Plan plan;

//    public UserCliente(String name, String lastname, String phone, String age, String email, String foto, String qr) {
//        super(name, lastname, phone, age, email, foto, qr);
//    }

    public UserCliente(Plan plan, String name, String lastname, String phone, String age, String email, String foto, String qr) {
        super(name, lastname, phone, age, email, foto, qr);
        this.plan = plan;
    }


    public void createCredentials() {
        super.createCredentials("Cliente");
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
    
    
}
