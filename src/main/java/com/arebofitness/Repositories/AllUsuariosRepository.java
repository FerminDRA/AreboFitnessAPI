/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Repositories;

import com.arebofitness.Models.ViewUsuarios;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fermin
 */
public interface AllUsuariosRepository extends JpaRepository<ViewUsuarios, Integer>{
    List<ViewUsuarios> findAll();
}
