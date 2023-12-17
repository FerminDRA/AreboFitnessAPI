/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.DTOs.AllPagosDTO;
import com.arebofitness.DTOs.PagoDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Models.Pago;
import com.arebofitness.Models.Plan;
import com.arebofitness.Models.UserCliente;
import com.arebofitness.Models.UserPersonal;
import com.arebofitness.Repositories.PagoRepository;
import com.arebofitness.Repositories.PlanRepository;
import com.arebofitness.Repositories.UsuarioCltRepository;
import com.arebofitness.Repositories.UsuarioPsnlRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author fermin
 */

@Service
public class PagoService {
    @Autowired
    private PagoRepository pgRep;
    @Autowired
    UsuarioCltRepository usrRep;
    @Autowired
    UsuarioPsnlRepository usrPrRep;
    @Autowired
    PlanRepository plnRep;
    
    public List<AllPagosDTO> allPagos(){
        List<Pago> pagos = pgRep.findAll();
        List<AllPagosDTO> allPg=new ArrayList<>();
//        for (Pago pago : pagos) {
//            Optional <UserCliente> clOpc=usrRep.findById(pago.getUsuarios().get(0));
//            AllPagosDTO allPagos=new AllPagosDTO(clOpc.get().getId_usuario(),
//                    clOpc.get().getName(), pago.getPlan().getName(),
//                    pago.getPlan().getDuration(), pago.getFechaPago(),
//                    pago.getMonto_pago());
//            allPg.add(allPagos);
//        }
        for (Pago pago : pagos) {
            //Optional <UserCliente> clOpc=usrRep.findById(pago.getUsuarios().get(0));
            //Optional <UserPersonal> psOpc=usrPrRep.findById(pago.getUsuario().getId_usuario());
            AllPagosDTO allPagos=new AllPagosDTO(pago.getUsuarios(),
                    pago.getUsuario().getName(), pago.getPlan().getName(),
                    pago.getPlan().getDuration(), pago.getFechaPago(),
                    pago.getMonto_pago());
            allPg.add(allPagos);
        }
        return allPg;
    }
    
    public List<AllPagosDTO> getPagosUsuario(String id){
        List<Pago> pagos = pgRep.findAll();
        List<AllPagosDTO> allPg=new ArrayList<>();
        for (Pago pago : pagos) {
//            for (String nm : pago.getUsuarios()) {
//                Optional <UserCliente> clOpc=usrRep.findById(nm);
//                if(clOpc.get().getId_usuario().equals(id)){
//                    AllPagosDTO allPagos=new AllPagosDTO(clOpc.get().getId_usuario(),
//                    clOpc.get().getName(), pago.getPlan().getName(),
//                    pago.getPlan().getDuration(), pago.getFechaPago(),
//                    pago.getMonto_pago());
//                    allPg.add(allPagos);
//                }
//            }
            for (String nm : pago.getUsuarios()) {
                Optional <UserCliente> clOpc=usrRep.findById(nm);
                if(clOpc.get().getId_usuario().equals(id)){
                    AllPagosDTO allPagos=new AllPagosDTO(pago.getUsuarios(),
                    clOpc.get().getName(), pago.getPlan().getName(),
                    pago.getPlan().getDuration(), pago.getFechaPago(),
                    pago.getMonto_pago());
                    allPg.add(allPagos);
                }
            }
        }
        if (!allPg.isEmpty()) {
            return allPg;
        }
        else{
            throw new DataException("No se encontraron pagos para el usuario con ID: " + id);
        }
        
    }
    
    public PagoDTO getPagoById(int id){
        Optional <Pago> opcPg=pgRep.findById(id);
        if(opcPg.isPresent()){
            Pago pago=opcPg.get();
            PagoDTO pgDto=new PagoDTO(pago.getId_pago(), pago.getUsuario().getName(),
                    pago.getUsuarios(), pago.getPlan().getId_plan(),
                    pago.getMonto_pago(), pago.getF_inicio(), pago.getF_fin(),
                    pago.getFechaPago(),pago.getComprobante());
            return pgDto;
        }
        else{
            throw new DataException("No se encontraro el usuario con ID: " + id);
        }
    }
    
    public PagoDTO createPago(PagoDTO pago){
        Optional<UserPersonal> opcUsr = usrPrRep.findById(pago.getId_usuario());
        //Optional<Admin> opcAdm = admRep.findById(pago.getId_admin());
        Optional<Plan> opcPln = plnRep.findById(pago.getId_plan());
        //Optional<Costo> opcCst = cstRep.findById(pago.getId_costo());

        if (opcUsr.isPresent() &&opcPln.isPresent()) {
            Pago newPago = pgRep.save(new Pago(pago.getUsuarios(), pago.getF_fin(),
                    pago.getF_fin(), pago.getFechapago(), pago.getComprobante(),
                    pago.getMonto_pago(), opcUsr.get(), opcPln.get()));
            //PagoDTO pgDTO=getPagoById(newPago.getId_pago());
            pago.setId_pago(newPago.getId_pago());
            return pago;
        }
        else{
            throw new IllegalArgumentException("Datos incompletos para crear el pago");
        }
    }
    
    public void deletePago(int id){
        Optional<Pago> opc=pgRep.findById(id);
        if(opc.isPresent()){
            pgRep.deleteById(id);
        }
        else{
            throw new DataException("No se encontro un pago con el id: "+id);
        }
    }
}
