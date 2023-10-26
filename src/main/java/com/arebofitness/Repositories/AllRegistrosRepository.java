/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Repositories;

import com.arebofitness.DTOs.AllRegistrosDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author fermin
 */
public interface AllRegistrosRepository extends JpaRepository<AllRegistrosDTO, Integer>{
    List<AllRegistrosDTO> findAll();
}
