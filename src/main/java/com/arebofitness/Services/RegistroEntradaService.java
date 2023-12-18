/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.DTOs.AllRegistrosDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Models.RegistroEntrada;
import com.arebofitness.Models.UserCliente;
import com.arebofitness.Repositories.AllRegistrosRepository;
import com.arebofitness.Repositories.RegistroEntradaRepository;
import com.arebofitness.Repositories.UsuarioCltRepository;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fermin
 */
@Service
public class RegistroEntradaService{
    @Autowired
    private RegistroEntradaRepository rgEnRep;
    @Autowired
    private AllRegistrosRepository allRgEnRep;
    @Autowired
    private UsuarioCltRepository usrRep;
    
    public AllRegistrosDTO registroEntrada(String id, RegistroEntrada reg) {
        Optional<UserCliente> opcUsr = usrRep.findById(id);
        if (opcUsr.isPresent()) {
            RegistroEntrada registro = rgEnRep.save(new RegistroEntrada(
                    reg.getH_entrada(),
                    reg.getFecha(),
                    opcUsr.get()
            ));
            AllRegistrosDTO register= new AllRegistrosDTO(registro.getId_registro(),
                    registro.getUsuario().getId_usuario(), registro.getUsuario().getName(),
                    registro.getH_entrada(), registro.getH_salida(),registro.getFecha());
            return register;
        }
        throw new DataException("Datos del registro vacios");
    }
    
    @Transactional
    public AllRegistrosDTO registroSalida(int id, RegistroEntrada reg) {
        Optional<RegistroEntrada> opcReg = rgEnRep.findById(id);
        if (opcReg.isPresent()) {
            RegistroEntrada registro = opcReg.get();
            registro.setH_salida(reg.getH_salida());
            rgEnRep.save(registro);
            AllRegistrosDTO register= new AllRegistrosDTO(registro.getId_registro(),
                    registro.getUsuario().getId_usuario(), registro.getUsuario().getName(),
                    registro.getH_entrada(), registro.getH_salida(),registro.getFecha());
            return register;
        }
        throw new DataException("No existe el registro con el id: "+id);
    }

    public void deleteRegistro(int id) {
        Optional<RegistroEntrada> opc=rgEnRep.findById(id);
        if(opc.isPresent()){
            rgEnRep.deleteById(id);
        }
        else{
            throw new DataException("No se encontro un registro con el id: "+id);
        }
    }

    public void updateRegistro() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public AllRegistrosDTO getRegistrobyID(int id) {
        Optional<AllRegistrosDTO> opc= allRgEnRep.findById(id);
        if(opc.isPresent()){
            return opc.get();
        }
        throw new DataException("No se encontro un registro con el id: "+id);
    }

    public List<AllRegistrosDTO> getAll() {
        List<AllRegistrosDTO> registros=allRgEnRep.findAll();
        if(!registros.isEmpty()){
            return registros;
        }
        throw new DataException("No hay registros");
    }
    
    public List<AllRegistrosDTO> getAllByFecha(Date fecha) {
        List<AllRegistrosDTO> registros=allRgEnRep.findAllByFecha(fecha);
        if(!registros.isEmpty()){
            return registros;
        }
        throw new DataException("No hay registros");
    }
}
