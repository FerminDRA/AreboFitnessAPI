/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.arebofitness.Repositories;

import com.arebofitness.Models.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fermin
 */
public interface HorarioRepository extends JpaRepository<Horario, Integer>{
    
}
