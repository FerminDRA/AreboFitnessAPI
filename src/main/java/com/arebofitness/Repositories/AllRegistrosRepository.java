/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Repositories;

import com.arebofitness.DTOs.AllRegistrosDTO;
import com.arebofitness.Models.RegistroEntrada;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fermin
 */
public interface AllRegistrosRepository extends JpaRepository<AllRegistrosDTO, Integer>{
    List<AllRegistrosDTO> findAll();
    @Query(value = "select * from allregisters where fecha=:fecha",nativeQuery = true)
    List<AllRegistrosDTO> findAllByFecha(@Param("fecha") Date fecha);
}
