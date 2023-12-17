/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.io.Serializable;
import javax.persistence.*;
import lombok.*;

/**
 *
 * @author zS20006736
 */
@NoArgsConstructor()
@Entity
@DiscriminatorValue("Personal")
public class UserPersonal extends User implements Serializable{

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_horario")
    @JsonBackReference
    private Horario horario;

//    public UserPersonal(String name, String lastname, String phone, String age, String email, String foto, String qr) {
//        super(name, lastname, phone, age, email, foto, qr);
//    }

    public UserPersonal(Horario horario, String name, String lastname, String phone, String age, String email, String foto, String qr) {
        super(name, lastname, phone, age, email, foto, qr);
        this.horario = horario;
        createCredentials();
    }
    
    public void createCredentials() {
        super.createCredentials("Personal");
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    
}
