package com.arebofitness.Controllers;

import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Models.Plan;
import com.arebofitness.Services.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/arebofitness/planes")
@CrossOrigin(origins = "*")
public class PlanController {
    @Autowired
    private PlanService plnServ;
    // Obtener todos los planes
    @GetMapping("/")
    public ResponseEntity<Object> getAllPlanes() {
        try {
            List<Plan> planes = plnServ.getAllPlanes();
            return ApiResponseHelper.ok("Planes encontrados",HttpStatus.OK, planes);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NO_CONTENT, null);
        }  catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Obtener un plan por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPlanById(@PathVariable int id) {
        try {
            Plan pln= plnServ.getPlanbyID(id);
            return ApiResponseHelper.ok("Plan encontrado",HttpStatus.OK, pln);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }

    // Crear un nuevo plan
    @PostMapping("/")
    public ResponseEntity<Object> createPlan(@RequestBody Plan plan) {        
        try {
            Plan nuevoPlan = plnServ.createPlan(plan);
            return ApiResponseHelper.ok("Plan creado", HttpStatus.CREATED, nuevoPlan);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_ACCEPTABLE, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Actualizar un plan existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePlan(@PathVariable int id, @RequestBody Plan plan) {
        try {
            plnServ.updatePlan(id,plan);
            return ApiResponseHelper.ok("Plan actualizado", HttpStatus.OK, null);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_ACCEPTABLE, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Eliminar un plan por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePlan(@PathVariable int id) {
        try {
            plnServ.deletePlan(id);
            return ApiResponseHelper.ok("Plan eliminado", HttpStatus.OK, null);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
