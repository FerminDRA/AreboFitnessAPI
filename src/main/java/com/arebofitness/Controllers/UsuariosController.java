/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Controllers;

import com.arebofitness.DTOs.AllUsersDTO;
import com.arebofitness.DTOs.GetUsuarioDTO;
import com.arebofitness.DTOs.UserListDTO;
import com.arebofitness.DTOs.UsuarioPagoDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Services.UsuarioClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fermin
 */
@RestController
@RequestMapping("/arebofitness/usuarios")
@CrossOrigin(origins = "*")
public class UsuariosController {

    @Autowired
    private UsuarioClienteService usrServ;
    
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllMembers() {
        try {
            List<AllUsersDTO> users = usrServ.getAll();
            return ApiResponseHelper.ok("All users",HttpStatus.OK, users);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NO_CONTENT, null);
        }  catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") String id) {
        try {
            GetUsuarioDTO usrDto=usrServ.getUser(id);
            return ApiResponseHelper.ok("Usuario encontrado",HttpStatus.OK, usrDto);
        }catch (DataException e) {
            return ApiResponseHelper.error(e.getMessage(), HttpStatus.NOT_FOUND, null);
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
    //@PostMapping("/{id_cst}")
    //@PathVariable("id_cst") int id_cst
    @PostMapping("/")
    //Revisar si el id de Costo se enviara por path variable o lo cargo en el pagoDTO
    public ResponseEntity<Object> addUser(@RequestBody UsuarioPagoDTO userPay) {
        try {
            Boolean res = usrServ.createUser(userPay);
            if (res) {
                return ApiResponseHelper.ok("Usuario creado", HttpStatus.OK, null);
            } else {
                return ApiResponseHelper.ok("Error al guardar usuario", HttpStatus.NOT_ACCEPTABLE, null);
            }
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
//    
//    @PutMapping("/")
//    public ResponseEntity<Usuario> editUsuario(@RequestParam int id_user, @RequestBody Usuario user){
//        //Verificar si se hace uso de DTO o el modelo
//        Optional<Usuario> opc=usrRep.findById(id_user);
//        if(opc.isPresent()){
//           Usuario usr= opc.get();
//           usr.setNombres(user.getNombres());
//           usr.setTelefono(user.getTelefono());
//           usr.setCorreo(user.getCorreo());
//           usr.setFoto_perfil(usr.getFoto_perfil());
//           return new ResponseEntity<>(usrRep.save(usr), HttpStatus.OK);
//        }
//        else{
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//        //return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
//    }
}
