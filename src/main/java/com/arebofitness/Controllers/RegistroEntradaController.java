/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Controllers;

import com.arebofitness.DTOs.AllRegistrosDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Models.RegistroEntrada;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.arebofitness.Services.RegistroEntradaService;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author fermin
 */
@RestController
@RequestMapping("/arebofitness/registros")
@CrossOrigin(origins = "*")
public class RegistroEntradaController {
    @Autowired
    private RegistroEntradaService regServ;
    
    @GetMapping("/")
    public ResponseEntity<Object> allRegistros(){
        try {
            List<AllRegistrosDTO> registros = regServ.getAll();
            return ApiResponseHelper.ok("Registros encontrado",HttpStatus.OK, registros);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
    @GetMapping("/{fecha}")
    public ResponseEntity<Object> allRegistrosFecha(@PathVariable("fecha") String fech){
        try {
            LocalDate fechsf = LocalDate.parse(fech, DateTimeFormatter.BASIC_ISO_DATE);
            List<AllRegistrosDTO> registros = regServ.getAllByFecha(Date.valueOf(fechsf));
            return ApiResponseHelper.ok("Registros encontrado",HttpStatus.OK, registros);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
    @PostMapping("/entrada/{id}")
    public ResponseEntity<Object> addRegistro(@RequestBody RegistroEntrada reg,@PathVariable("id") String id_usuario) {
        try {
            AllRegistrosDTO registro=regServ.registroEntrada(id_usuario, reg);
            return ApiResponseHelper.ok("Registro creado",HttpStatus.OK, registro);
        } catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
    
    @PostMapping("/salida/{id}")
    public ResponseEntity<Object> addRegistroSalida(@RequestBody RegistroEntrada reg,@PathVariable("id") int id_registro) {
        try {
            AllRegistrosDTO registro=regServ.registroSalida(id_registro, reg);
            return ApiResponseHelper.ok("Salida registrada",HttpStatus.OK, registro);
        } catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlan(@PathVariable int id) {
        try {
            regServ.deleteRegistro(id);
            return ApiResponseHelper.ok("Registro eliminado", HttpStatus.OK, null);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
