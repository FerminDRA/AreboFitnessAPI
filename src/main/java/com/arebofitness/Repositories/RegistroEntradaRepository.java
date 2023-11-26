/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.arebofitness.Repositories;

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
public interface RegistroEntradaRepository extends JpaRepository<RegistroEntrada, Integer>{
    @Query(value = "select * from registro_entradas where fecha=:fecha",nativeQuery = true)
    List<RegistroEntrada> findAllByFecha(@Param("fecha") Date fecha);
}
