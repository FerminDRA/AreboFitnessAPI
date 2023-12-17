/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.DTOs;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author fermin
 */
@Data
@NoArgsConstructor
public class GetPagoDTO {
    private int id_pago;
    private List<Map<String, String>> usuarios = new ArrayList<>();
    private String nmPlan;
    private BigDecimal monto_pago;
    private Timestamp fechapago;
    private String comprobante;
    
    //Get by id de pago
    //lista usuarios de pagos nombre e id, comprobante

    public GetPagoDTO(int id_pago,List<Map<String, String>> usuarios, String nmPlan, BigDecimal monto_pago, Timestamp fechapago, String comprobante) {
        this.id_pago = id_pago;
        this.usuarios=usuarios;
        this.nmPlan = nmPlan;
        this.monto_pago = monto_pago;
        this.fechapago = fechapago;
        this.comprobante = comprobante;
    }
    
    
}
