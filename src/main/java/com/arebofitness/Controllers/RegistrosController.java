/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Controllers;

import com.arebofitness.DTOs.AllRegistrosDTO;
import com.arebofitness.Models.RegistroEntrada;
import com.arebofitness.Models.Usuario;
import com.arebofitness.Repositories.AllRegistrosRepository;
import com.arebofitness.Repositories.RegistroEntradaRepository;
import com.arebofitness.Repositories.UsuarioRepository;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fermin
 */
@RestController
@RequestMapping("/arebofitness/registros")
public class RegistrosController {
    @Autowired
    RegistroEntradaRepository regEntRep;
    @Autowired
    UsuarioRepository usrRep;
    @Autowired
    AllRegistrosRepository allRegRep;
    
    @GetMapping("/")
    public ResponseEntity<List<AllRegistrosDTO>> allRegistros(){
        try {
            List<AllRegistrosDTO> registros = allRegRep.findAll();
            return new ResponseEntity<>(registros, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("/")
    public ResponseEntity<HttpStatus> addRegistro(@RequestParam int id_usuario) {
        try {
            Optional<Usuario> opcUsr=usrRep.findById(id_usuario);
            if (!opcUsr.isEmpty()) {
                LocalTime horaActual = LocalTime.now();
                Date date=new Date();
                Time hora = Time.valueOf(horaActual);
                java.sql.Date fecha = new java.sql.Date(date.getTime());
                regEntRep.save(new RegistroEntrada(
                        hora,
                        fecha,
                        opcUsr.get()
                ));
               return new ResponseEntity<>(null, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }
}
