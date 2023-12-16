package com.arebofitness.Controllers;

import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Models.Horario;
import com.arebofitness.Services.HorariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arebofitness/horarios")
@CrossOrigin(origins = "*")
public class HorarioController {
    @Autowired
    private HorariosService hrrServ;

    // Obtener todos los horarios
    @GetMapping("/")
    public ResponseEntity<Object> getAllHorarios() {
        try {
            List<Horario> hrrs = hrrServ.getAll();
            return ApiResponseHelper.ok("Horarios encontrados",HttpStatus.OK, hrrs);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NO_CONTENT, null);
        }  catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Obtener un horario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Object> getHorarioById(@PathVariable int id) {
        try {
            Horario hrr= hrrServ.getHorariobyID(id);
            return ApiResponseHelper.ok("Plan encontrado",HttpStatus.OK, hrr);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }

    // Crear un nuevo horario
    @PostMapping("/")
    public ResponseEntity<Object> createHorario(@RequestBody Horario horario) {
        try {
            Horario nwHrr = hrrServ.createHorario(horario);
            return ApiResponseHelper.ok("Horario creado", HttpStatus.CREATED, nwHrr);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_ACCEPTABLE, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Actualizar un horario existente
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHorario(@PathVariable int id, @RequestBody Horario horario) {
        try {
            hrrServ.updateHorario(id,horario);
            return ApiResponseHelper.ok("Horario actualizado", HttpStatus.OK, null);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_ACCEPTABLE, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }

    // Eliminar un horario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHorario(@PathVariable int id) {
        try {
            hrrServ.deleteHorario(id);
            return ApiResponseHelper.ok("Horario eliminado", HttpStatus.OK, null);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
