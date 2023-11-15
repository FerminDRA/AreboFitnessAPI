/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Controllers;

import com.arebofitness.DTOs.AllUsersDTO;
import com.arebofitness.DTOs.GetUsuarioDTO;
import com.arebofitness.DTOs.PagoDTO;
import com.arebofitness.DTOs.UsuarioDTO;
import com.arebofitness.Models.ViewUsuarios;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Helpers.MessageHelper;
import com.arebofitness.Models.Pago;
import com.arebofitness.Models.Plan;
import com.arebofitness.Models.TipoUsuario;
import com.arebofitness.Models.Usuario;
import com.arebofitness.Repositories.AllUsuariosRepository;
import com.arebofitness.Repositories.HorarioRepository;
import com.arebofitness.Repositories.PagoRepository;
import com.arebofitness.Repositories.PlanRepository;
import com.arebofitness.Repositories.TipoUsuarioRepository;
import com.arebofitness.Repositories.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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
    @Autowired
    PagoRepository pgRep;
    
    
    @GetMapping("/")
    public ResponseEntity<Object> getAllMembers() {
        try {
            List<ViewUsuarios> usuarios = allUsrRep.findAll();
            List<AllUsersDTO> users = new ArrayList<>();
            for (ViewUsuarios usuario : usuarios) {
                Optional<Usuario> opcUsr = usrRep.findById(usuario.getId_usuario());
                if(!opcUsr.isEmpty()){
                    Pago pg=pgRep.getPaymentByUserId(usuario.getId_usuario());
                    users.add(new AllUsersDTO(usuario.getId_usuario(),usuario.getNombres(),usuario.getTelefono(),
                            usuario.getNombreplan(),usuario.getPeriodo(),pg.getF_fin(),usuario.getCosto()));
                }
            }
            return ApiResponseHelper.ok("All users",HttpStatus.OK, users);
        } catch (Exception e) {
            return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR, null);
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable("id") int id) {
        Optional<Usuario> usr = usrRep.findById(id);
        try {
            if (usr.isPresent()) {
                Usuario user = usr.get();
                GetUsuarioDTO usrDto= new GetUsuarioDTO(user.getId_usuario(),user.getNombres(),user.getApellidos(),
                        user.getEdad(),user.getTelefono(),user.getCorreo(),user.getUsrTipe().getNombre(),
                        user.getPlan().getNombre()
                );
                String successMessage = MessageHelper.successMessage("usuario", "creado");
                //return new ResponseEntity<>(users, HttpStatus.OK);
                return ApiResponseHelper.ok(successMessage,HttpStatus.OK, usrDto);
            } else {
                String errorMessage = MessageHelper.errorMessage("usuario", "actualizar");
                return ApiResponseHelper.error(errorMessage,HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
                return ApiResponseHelper.error("Error de peticion:"+e.getMessage(),HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
    @Transactional
    //@PostMapping("/{id_cst}")
    //@PathVariable("id_cst") int id_cst
    @PostMapping("/")
    //Revisar si el id de Costo se enviara por path variable o lo cargo en el pagoDTO
    public ResponseEntity<Usuario> addUser(@RequestBody Map<String, Object> payload) {
        try {
            Map<String, Object> usuarioMap = (Map<String, Object>) payload.get("usuarioDTO");
            UsuarioDTO user=new ObjectMapper().convertValue(usuarioMap, UsuarioDTO.class);
            
            Map<String, Object> pagoMap = (Map<String, Object>) payload.get("pagoDTO");
            PagoDTO pagoDTO = new ObjectMapper().convertValue(pagoMap, PagoDTO.class);
            Optional<TipoUsuario> opctpUsr=tpUsrRep.findById(user.getId_tipo());
            Optional<Plan> opcPl=plRep.findById(user.getId_plan());
            if (user!= null&&opctpUsr.isPresent()&&opcPl.isPresent()) {
                Usuario usr = usrRep.save(new Usuario(
                        user.getNombres(),
                        user.getApellidos(),
                        user.getEdad(),
                        user.getTelefono(),
                        user.getCorreo(),
                        null,
                        opctpUsr.get(),
                        opcPl.get()
                ));
                
                //Pago pago= 
                return new ResponseEntity<>(usr, HttpStatus.CREATED);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        //return null;
    }
    
    
    //Partir la tabla para los empleados y usuarios
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
