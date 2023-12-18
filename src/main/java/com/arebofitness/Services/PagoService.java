/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.DTOs.AllPagosDTO;
import com.arebofitness.DTOs.GetPagoDTO;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    UsuarioClienteService usrClSer;
    
    @Transactional
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
            AllPagosDTO allPagos=new AllPagosDTO(pago.getId_pago(),
                    pago.getUsuario().getName(), pago.getPlan().getName(),
                    pago.getPlan().getDuration(), pago.getFechaPago(),
                    pago.getMonto_pago(),pago.getComprobante());
            allPg.add(allPagos);
        }
        return allPg;
    }
    
    public List<GetPagoDTO> getPagosUsuario(String id){
        List<Pago> pagos = pgRep.findAll();
        List<GetPagoDTO> allPg=new ArrayList<>();
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
                    List<Map<String, String>> usuarios = new ArrayList<>();
                    for (String idcls : pago.getUsuarios()) {
                        Optional<UserCliente> opcCls = usrRep.findById(idcls);
                        if (opcCls.isPresent()) {
                            UserCliente usr = opcCls.get();
                            Map<String, String> datos = new HashMap<>();
                            datos.put("id", usr.getId_usuario());
                            datos.put("nombre", usr.getName());
                            usuarios.add(datos);
                        }
                    }
                    GetPagoDTO allPagos=new GetPagoDTO(pago.getId_pago(),usuarios, pago.getPlan().getName(),
                    pago.getMonto_pago(), pago.getFechaPago(),
                    pago.getComprobante());
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
    
    public GetPagoDTO getPagoById(int id){
        Optional <Pago> opcPg=pgRep.findById(id);
        if(opcPg.isPresent()){
            Pago pago=opcPg.get();
            List<Map<String, String>> usuarios = new ArrayList<>();
            for(String idcls:pago.getUsuarios()){
                Optional<UserCliente> opcCls=usrRep.findById(idcls);
                if(opcCls.isPresent()){
                    UserCliente usr=opcCls.get();
                    Map<String, String> datos = new HashMap<>();
                    datos.put("id",usr.getId_usuario());
                    datos.put("nombre", usr.getName());
                    usuarios.add(datos);
                }
            }
            GetPagoDTO pgDto=new GetPagoDTO(pago.getId_pago(),usuarios, pago.getPlan().getName(),
                    pago.getMonto_pago(), pago.getFechaPago(),
                    pago.getComprobante());
            return pgDto;
        }
        else{
            throw new DataException("No se encontraro el usuario con ID: " + id);
        }
    }
    
    public PagoDTO createPago(PagoDTO pago){
        Optional<UserPersonal> opcUsr = usrPrRep.findById(pago.getId_usuario());
        Optional<Plan> opcPln = plnRep.findById(pago.getId_plan());
        List<Boolean> resServ=usrClSer.validarUsers(pago.getUsuarios());
        boolean res = resServ.stream().allMatch(valor -> valor);
        if (opcUsr.isPresent() &&opcPln.isPresent() && res) {
            for(String idUsr:pago.getUsuarios()){
                Optional<UserCliente> opcUsrCL=usrRep.findById(idUsr);
                UserCliente cliente=opcUsrCL.get();
                cliente.setPlan(opcPln.get());
                usrRep.save(cliente);
            }
            Pago newPago = pgRep.save(new Pago(pago.getUsuarios(), pago.getF_inicio(),
                    pago.getF_fin(), pago.getF_inicio(), pago.getComprobante(),
                    opcPln.get().getCost(), opcUsr.get(), opcPln.get()));
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
