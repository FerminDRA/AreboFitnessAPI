/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.arebofitness.Repositories;

import com.arebofitness.Models.Pago;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author fermin
 */
public interface PagoRepository extends JpaRepository<Pago, Integer>{
    @Query(value = "select * from pagos where id_usuario=:id ORDER BY id_pago DESC LIMIT 1;",nativeQuery = true)
    Pago getPaymentByUserId(@Param("id") String id);
    
    @Query(value = "select * from pagos where id_usuario=:id ORDER BY id_pago;",nativeQuery = true)
    List<Pago> getAllPaymentsByUserId(@Param("id") Integer id);
}
