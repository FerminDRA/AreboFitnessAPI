/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Models.Plan;
import com.arebofitness.Repositories.PlanRepository;
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
public class PlanService {
    @Autowired
    private PlanRepository plnRep;
    
    public Plan createPlan(Plan pln){
        if(pln.getCost()!=null){
            return plnRep.save(pln);
        }
        throw new DataException("Datos del plan vacios");
    }
    
    //@Transactional
    public void deletePlan(int id){
        Optional<Plan> opc=plnRep.findById(id);
        if(opc.isPresent()){
            plnRep.deleteById(id);
        }
        else{
            throw new DataException("No se encontro un plan con el id: "+id);
        }
    }
    
    //editar
    public Plan updatePlan(int id,Plan pln){
        Optional<Plan> opc= plnRep.findById(id);
        if(opc.isPresent()){
            Plan plan=opc.get();
            plan.setName(pln.getName());
            plan.setDuration(pln.getDuration());
            plan.setCost(pln.getCost());
            plan.setMaxMember(pln.getMaxMember());
            plan.setDays(pln.getDays());
            //plnRep.deleteById(pln.getId_plan());
            return plnRep.save(pln);
        }
        throw new DataException("No se encontro un plan con el id: "+id);
    }
    
    public Plan getPlanbyID(int id){
        Optional<Plan> opc= plnRep.findById(id);
        if(opc.isPresent()){
            return opc.get();
        }
        throw new DataException("No se encontro un plan con el id: "+id);
    }
    
    public List<Plan> getAllPlanes(){
        List<Plan> planes=plnRep.findAll();
        if(!planes.isEmpty()){
            return planes;
        }
        throw new DataException("No hay registros de planes");
    }
}
