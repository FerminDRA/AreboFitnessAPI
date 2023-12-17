/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.arebofitness.Controllers;

import com.arebofitness.DTOs.UserListDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Services.UsuarioPersonalService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author fermin
 */
@RestController
@RequestMapping("/arebofitness/empleado")
@CrossOrigin(origins = "*")
public class UsuarioPersonalController {
    @Autowired
    UsuarioPersonalService usrPsrlServ;
    
    @GetMapping()
    public ResponseEntity<Object> getAllMembers() {
        try {
            List<UserListDTO> users = usrPsrlServ.getAllPersonal();
            return ApiResponseHelper.ok("Todo el personal",HttpStatus.OK, users);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NO_CONTENT, null);
        }  catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getPersonalById(@PathVariable("id") String id) {
        try {
            UserListDTO usrDto=usrPsrlServ.getPersonalbyID(id);
            return ApiResponseHelper.ok("Personal encontrado",HttpStatus.OK, usrDto);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> put(@PathVariable String id, @RequestBody Object input) {
        return null;
    }
    
    @PostMapping("/{id}")
    public ResponseEntity<Object> addPersonal(@RequestBody UserListDTO user, @PathVariable("id") int id){
        try {
            Boolean res = usrPsrlServ.createPersonal(user,id);
            if (res) {
                return ApiResponseHelper.ok("Personal creado", HttpStatus.OK, null);
            } else {
                return ApiResponseHelper.ok("Error al guardar Personal", HttpStatus.NOT_ACCEPTABLE, null);
            }
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePago(@PathVariable String id) {
        try {
            usrPsrlServ.deletePersonal(id);
            return ApiResponseHelper.ok("Personal eliminado", HttpStatus.OK, null);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
            return ApiResponseHelper.error("Erro de peticion: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
}
