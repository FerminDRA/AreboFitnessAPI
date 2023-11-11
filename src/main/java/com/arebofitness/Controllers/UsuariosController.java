/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Controllers;

import com.arebofitness.DTOs.AllUsuariosDTO;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Helpers.MessageHelper;
import com.arebofitness.Models.Plan;
import com.arebofitness.Models.TipoUsuario;
import com.arebofitness.Models.Usuario;
import com.arebofitness.Repositories.AllUsuariosRepository;
import com.arebofitness.Repositories.HorarioRepository;
import com.arebofitness.Repositories.PlanRepository;
import com.arebofitness.Repositories.TipoUsuarioRepository;
import com.arebofitness.Repositories.UsuarioRepository;
import com.arebofitness.Response.ResponseHandling;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fermin
 */
@RestController
@RequestMapping("/arebofitness/usuarios")
public class UsuariosController {

    @Autowired
    UsuarioRepository usrRep;
    @Autowired
    AllUsuariosRepository allUsrRep;
    @Autowired
    PlanRepository plRep;
    @Autowired
    HorarioRepository horRep;
    @Autowired
    TipoUsuarioRepository tpUsrRep;
    
    
    @GetMapping("/")
    public ResponseEntity<List<AllUsuariosDTO>> getAllMembers() {
        try {
            List<AllUsuariosDTO> usuarios = allUsrRep.findAll();
            for (AllUsuariosDTO usuario : usuarios) {
                Optional<Usuario> usr = usrRep.findById(usuario.getId_usuario());
                if(!usr.isEmpty()){
                    
                }
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
            //String successMessage = MessageHelper.successMessage("usuario", "creado");
            //return ApiResponseHelper.ok(successMessage,HttpStatus.OK, usuarios);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //testHelpers
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id) {
        Optional<Usuario> usr = usrRep.findById(id);
        try {
            if (!usr.isEmpty()) {
                Usuario users = usr.get();
                String successMessage = MessageHelper.successMessage("usuario", "creado");
                //return new ResponseEntity<>(users, HttpStatus.OK);
                return ApiResponseHelper.ok(successMessage,HttpStatus.OK, users);
            } else {
                String errorMessage = MessageHelper.errorMessage("usuario", "actualizar");
                return ApiResponseHelper.error(errorMessage,HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
//    public ResponseEntity<Usuario> getUserById(@PathVariable("id") int id) {
//        Optional<Usuario> usr = usrRep.findById(id);
//        if (!usr.isEmpty()) {
//            Usuario users = usr.get();
//            return new ResponseEntity<>(users, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
//        }
//    }
    
    @PostMapping("/test/")
    public ResponseEntity<Usuario> addUser(@RequestBody Usuario user, 
            @RequestParam int id_tipo,@RequestParam int id_plan) {
        try {
            Optional<TipoUsuario> opctpUsr=tpUsrRep.findById(id_tipo);
            Optional<Plan> opcPl=plRep.findById(id_plan);
            //Optional<HorarioRepository> opctpUsr=tpUsrRep.findById(id_plan);
            if (user != null&&!opctpUsr.isEmpty()&&!opcPl.isEmpty()) {
                Usuario usr = usrRep.save(new Usuario(
                        user.getNombres(),
                        user.getApellidos(),
                        user.getEdad(),
                        user.getTelefono(),
                        user.getCorreo(),
                        user.getFoto_perfil(),
                        opctpUsr.get(),
                        opcPl.get()
                        
                ));
                return new ResponseEntity<>(usr, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        //return null;
    }
    
    @PostMapping("/empleado/")
    public ResponseEntity<Usuario> addEmployee(@RequestBody Usuario user, 
            @RequestParam int id_tipo) {
        try {
            Optional<TipoUsuario> opctpUsr=tpUsrRep.findById(id_tipo);
            //Optional<HorarioRepository> opctpUsr=tpUsrRep.findById(id_plan);
            if (user != null&&!opctpUsr.isEmpty()) {
                Usuario usr = usrRep.save(new Usuario(
                        user.getNombres(),
                        user.getApellidos(),
                        user.getEdad(),
                        user.getTelefono(),
                        user.getCorreo(),
                        user.getFoto_perfil(),
                        opctpUsr.get()
                        
                ));
                return new ResponseEntity<>(usr, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        //return null;
    }
    
    @PutMapping("/")
    public ResponseEntity<Usuario> editUsuario(@RequestParam int id_user, @RequestBody Usuario user){
        //Verificar si se hace uso de DTO o el modelo
        Optional<Usuario> opc=usrRep.findById(id_user);
        if(opc.isPresent()){
           Usuario usr= opc.get();
           usr.setNombres(user.getNombres());
           usr.setTelefono(user.getTelefono());
           usr.setCorreo(user.getCorreo());
           usr.setFoto_perfil(usr.getFoto_perfil());
           return new ResponseEntity<>(usrRep.save(usr), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        //return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
    }
}
