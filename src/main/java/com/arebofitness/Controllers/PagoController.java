package com.arebofitness.Controllers;

import com.arebofitness.DTOs.AllPagosDTO;
import com.arebofitness.DTOs.PagoDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arebofitness/pagos")
@CrossOrigin(origins = "*")
public class PagoController {
    
    @Autowired
    private PagoService pgServ;

    // Obtener todos los pagos
    @GetMapping("/")
    public ResponseEntity<Object> getAllPagos() {
        List<AllPagosDTO> pagos = pgServ.allPagos();
        return ApiResponseHelper.ok("null",HttpStatus.OK, pagos);
    }

    //Obtener pago por id usuario
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPagoByUserId(@PathVariable String id) {
        try {
            List<AllPagosDTO> pgDto = pgServ.getPagosUsuario(id);
            return ApiResponseHelper.ok("Pagos encontrados", HttpStatus.OK, pgDto);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            // Manejar otras excepciones no específicas aquí si es necesario
            return ApiResponseHelper.error("Erro de peticion: " +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }   
    
    //Obtener pago por id
    @GetMapping("/pg/{id}")
    public ResponseEntity<Object> getPagoById(@PathVariable int id) {
        try {
            PagoDTO pgDto = pgServ.getPagoById(id);
            return ApiResponseHelper.ok("Pagos encontrados", HttpStatus.OK, pgDto);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            // Manejar otras excepciones no específicas aquí si es necesario
            return ApiResponseHelper.error("Erro de peticion: " +e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }


    //Crear pago
    @PostMapping("/")
    public ResponseEntity<Object> guardarPago(@RequestBody PagoDTO pago) {
        try {
            PagoDTO pgDTO=pgServ.createPago(pago);
            return ApiResponseHelper.ok("Pago realizado", HttpStatus.OK, pgDTO);
        }catch (IllegalArgumentException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.BAD_REQUEST, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Erro al crear el pago: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
//
//    // Actualizar un pago existente
//    @PutMapping("/{id}")
//    public ResponseEntity<Pago> updatePago(@PathVariable int id, @RequestBody Pago pago) {
//        if (pgRep.existsById(id)) {
//            pago.setId_pago(id);
//            Pago pagoActualizado = pgRep.save(pago);
//            return new ResponseEntity<>(pagoActualizado, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
//
    // Eliminar un pago por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePago(@PathVariable int id) {
        try {
            pgServ.deletePago(id);
            return ApiResponseHelper.ok("Pago realizado", HttpStatus.OK, null);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Erro de peticion: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
