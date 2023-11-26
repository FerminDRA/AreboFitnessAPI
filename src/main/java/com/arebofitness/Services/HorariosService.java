/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Models.Horario;
import com.arebofitness.Repositories.HorarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fermin
 */
@Service
public class HorariosService{
    @Autowired
    private HorarioRepository hrrRep;

    
    public Horario createHorario(Horario hrr) {
        if(hrr.getHora_entrada()!=null&&hrr.getHora_salida()!=null){
            return hrrRep.save(hrr);
        }
        throw new DataException("Datos vacios para el horario");
    }

    
    public void deleteHorario(int id) {
        Optional<Horario> opc= hrrRep.findById(id);
        if(opc.isPresent()){
            hrrRep.deleteById(id);
        }
        else{
            throw new DataException("No se encontro el horario con id"+id);
        }
    }

    
    public Horario updateHorario(int id,Horario hrr) {
        Optional<Horario> opc=hrrRep.findById(id);
        if(opc.isPresent()){
            Horario horario=opc.get();
            horario.setHora_entrada(hrr.getHora_entrada());
            horario.setHora_salida(hrr.getHora_salida());
            return hrrRep.save(horario);
        }
        throw new DataException("No se encontro un horario con el id: "+id);
    }

    
    public Horario getHorariobyID(int id) {
        Optional<Horario> opc= hrrRep.findById(id);
        if(opc.isPresent()){
            return opc.get();
        }
        throw new DataException("No se encontro un horario con el id: "+id);
    }

    
    public List<Horario> getAll() {
        List<Horario> hrr=hrrRep.findAll();
        if(!hrr.isEmpty()){
            return hrr;
        }
        throw new DataException("No hay registros de horarios");
    }
    
}
