package com.arebofitness.Controllers;

import com.arebofitness.DTOs.GetPagoDTO;
import com.arebofitness.DTOs.PagoDTO;
import com.arebofitness.Exceptions.DataNotFoundException;
import com.arebofitness.Exceptions.InvalidIdException;
import com.arebofitness.Helpers.ApiResponseHelper;
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
import com.arebofitness.Services.PagoService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/pagos")
@CrossOrigin(origins = "*")
public class PagoController {
    private final PagoRepository pgRep;
    @Autowired
    CostoRepository cstRep;
    @Autowired
    UsuarioRepository usrRep;
    @Autowired
    PlanRepository plnRep;
    @Autowired
    AdminRepository admRep;
    
    @Autowired
    private PagoService pgServ;
    

    @Autowired
    public PagoController(PagoRepository pagoRepository) {
        this.pgRep = pagoRepository;
    }

    // Obtener todos los pagos
    @GetMapping("/")
    public ResponseEntity<Object> getAllPagos() {
        List<Pago> pagos = pgRep.findAll();
        return ApiResponseHelper.ok("null",HttpStatus.OK, pagos);
    }

    // Obtener un pagos por el id de USUARIO
    //id pago- Numero de pago del usuario, nombre del admin, nombre del plan, periodo de susc, fechas, costo,URLComprobante
    //Modificacion por el uso de una vista de pagos
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPagoByUserId(@PathVariable int id) {
        try {
            List<GetPagoDTO> pgDto = pgServ.getPagosUsuario(id);
            return ApiResponseHelper.ok("Pagos encontrados", HttpStatus.OK, pgDto);
        }catch (DataNotFoundException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            // Manejar otras excepciones no específicas aquí si es necesario
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
//    @GetMapping("/{id}")//Codigo Originalv1
//    public ResponseEntity<Object> getPagoByUserId(@PathVariable int id) {
//        List<Pago> pagos=pgRep.getAllPaymentsByUserId(id);
//        //Optional<Pago> optionalPago = pagoRepository.findById(id);
//        if (pagos!=null) {
//            //PagoDTO pgDto=new GetPagoDTO(pg)
//            List<PagoDTO> pgDto=new ArrayList<>();
//            int i=1;
//            for (Pago pago : pagos) {
//                //REvisar como hacer la relacion entre periodo de los Costos y Pagos
//                Optional<Costo> cstOpc=cstRep.findById(pago.getPlan().getId_plan());
//                Costo cst=cstOpc.get();
//                pgDto.add(new GetPagoDTO(i,pago.getAdmin().getNombre(),pago.getPlan().getNombre(),
//                        "Periodo de meses FIX",pago.getF_inicio(),pago.getF_fin(),pago.getMonto_pago()
//                ));
//                i++;
//            }
//            return ApiResponseHelper.ok("null",HttpStatus.OK, pgDto);
//        } else {
//            return ApiResponseHelper.error("null",HttpStatus.NOT_FOUND, null);
//        }
//    }

    // Crear un nuevo pago
    //idpago,idusuario,idadmin,idplan,finicio
    @PostMapping("/")
    public ResponseEntity<Object> guardarPago(@RequestBody PagoDTO pago) {
        try {
            PagoDTO pgDTO=pgServ.createPago(pago);
            return ApiResponseHelper.ok("Created Payment", HttpStatus.OK, pgDTO);
        }catch (IllegalArgumentException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Failed to create payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Actualizar un pago existente
    @PutMapping("/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable int id, @RequestBody Pago pago) {
        if (pgRep.existsById(id)) {
            pago.setId_pago(id);
            Pago pagoActualizado = pgRep.save(pago);
            return new ResponseEntity<>(pagoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un pago por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable int id) {
        if (pgRep.existsById(id)) {
            pgRep.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
