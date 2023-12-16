/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Repositories;

import com.arebofitness.Models.UserPersonal;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fermin
 */
public interface UsuarioPsnlRepository extends JpaRepository<UserPersonal, String>{
    
}
