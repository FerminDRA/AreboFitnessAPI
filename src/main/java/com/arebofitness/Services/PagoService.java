/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.DTOs.GetPagoDTO;
import com.arebofitness.DTOs.PagoDTO;
import com.arebofitness.Exceptions.DataNotFoundException;
import com.arebofitness.Models.Admin;
import com.arebofitness.Models.Costo;
import com.arebofitness.Models.Pago;
import com.arebofitness.Models.Plan;
import com.arebofitness.Models.Usuario;
import com.arebofitness.Repositories.AdminRepository;
import com.arebofitness.Repositories.CostoRepository;
import com.arebofitness.Repositories.PagoRepository;
import com.arebofitness.Repositories.PlanRepository;
import com.arebofitness.Repositories.UsuarioRepository;
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
    CostoRepository cstRep;
    @Autowired
    UsuarioRepository usrRep;
    @Autowired
    PlanRepository plnRep;
    @Autowired
    AdminRepository admRep;
    
//    public Listcw(){
//        List<Pago> pagos = pgRep.findAll();
//    }
    
    public List<GetPagoDTO> getPagosUsuario(int id){
        List<Pago> pagos = pgRep.getAllPaymentsByUserId(id);
        List<GetPagoDTO> pgDto = new ArrayList<>();
        if (!pagos.isEmpty()) {
            int i = 1;
            for (Pago pago : pagos) {
                Optional<Costo> cstOpc = cstRep.findById(pago.getPlan().getId_plan());
                Costo cst = cstOpc.get();
                pgDto.add(new GetPagoDTO(i, pago.getAdmin().getNombre(), pago.getPlan().getNombre(),
                        cst.getPeriodo(), pago.getF_inicio(), pago.getF_fin(), pago.getMonto_pago()
                ));
                i++;
            }
            return pgDto;
        }
        else{
            throw new DataNotFoundException("No se encontraron pagos para el usuario con ID: " + id);
        }
        
    }
    
    public PagoDTO getPagoById(int id){
        Optional <Pago> opcPg=pgRep.findById(id);
        if(opcPg.isPresent()){
            Pago pago=opcPg.get();
            PagoDTO pgDto=new PagoDTO(pago.getId_pago(),pago.getUsuario().getId_usuario(),
                    pago.getAdmin().getId_admin(),pago.getPlan().getId_plan(),
                    pago.getF_inicio(), pago.getF_fin(), pago.getComprobante());
            return pgDto;
        }
        else{
            throw new DataNotFoundException("No se encontraro el usuario con ID: " + id);
        }
    }
    
    public PagoDTO createPago(PagoDTO pago){
        Optional<Usuario> opcUsr = usrRep.findById(pago.getId_usuario());
        Optional<Admin> opcAdm = admRep.findById(pago.getId_admin());
        Optional<Plan> opcPln = plnRep.findById(pago.getId_plan());
        Optional<Costo> opcCst = cstRep.findById(pago.getId_costo());

        if (opcUsr.isPresent() && opcAdm.isPresent() && opcPln.isPresent() && opcCst.isPresent()) {
            Pago newPago = pgRep.save(new Pago(
                    pago.getF_inicio(),
                    pago.getF_fin(),
                    pago.getComprobante(),
                    opcCst.get().getCosto(),
                    opcUsr.get(),
                    opcAdm.get(),
                    opcPln.get()
            ));
            PagoDTO pgDTO=getPagoById(newPago.getId_pago());
            return pgDTO;
        }
        else{
            throw new IllegalArgumentException("Datos incompletos para crear el pago");
        }
    }
}
