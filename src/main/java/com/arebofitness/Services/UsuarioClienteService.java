/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.DTOs.AllUsersDTO;
import com.arebofitness.DTOs.GetUsuarioDTO;
import com.arebofitness.DTOs.UserListDTO;
import com.arebofitness.DTOs.UsuarioPagoDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Models.Pago;
import com.arebofitness.Models.Plan;
import com.arebofitness.Models.UserCliente;
import com.arebofitness.Models.UserPersonal;
import com.arebofitness.Models.ViewUsuarios;
import com.arebofitness.Repositories.AllUsuariosRepository;
import com.arebofitness.Repositories.HorarioRepository;
import com.arebofitness.Repositories.PagoRepository;
import com.arebofitness.Repositories.PlanRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.arebofitness.Repositories.UsuarioCltRepository;
import com.arebofitness.Repositories.UsuarioPsnlRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author fermin
 */
@Service
@Transactional
public class UsuarioClienteService {
    @Autowired
    UsuarioCltRepository usrClRep;
    @Autowired
    UsuarioPsnlRepository usrPrslRep;
    @Autowired
    UsuarioPsnlRepository usrPrsRep;
    @Autowired
    AllUsuariosRepository allUsrRep;
    @Autowired
    PlanRepository plRep;
    @Autowired
    PagoRepository pgRep;
    @Autowired
    HorarioRepository hroRep;

    
    public List<AllUsersDTO> getAll(){
        List<ViewUsuarios> usuarios = allUsrRep.findAll();
        if(!usuarios.isEmpty()){
            List<AllUsersDTO> users = new ArrayList<>();
            for (ViewUsuarios usuario : usuarios) {
                Optional<UserCliente> opcUsr = usrClRep.findById(usuario.getId_usuario());
                if (!opcUsr.isEmpty()) {
                    //Pago pg = pgRep.getPaymentByUserId(usuario.getId_usuario());
                    users.add(new AllUsersDTO(usuario.getId(),usuario.getId_usuario(), usuario.getNombres(), usuario.getTelefono(),
                            usuario.getNombreplan(),usuario.getDuracion(),usuario.getTermino(), usuario.getCosto()));
                }
            }
            return users;
        }
        else{
            throw new DataException("No hay usuarios");
        }
    }
    
    public GetUsuarioDTO getUser(String id){
        Optional<UserCliente> usr = usrClRep.findById(id);
        if (usr.isPresent()) {
            UserCliente user = usr.get();
            GetUsuarioDTO usrDto = new GetUsuarioDTO(user.getId_usuario(), user.getName(), user.getLastname(),
                    user.getAge(), user.getPhone(), user.getEmail(),user.getPlan().getName()
            );
            //return new ResponseEntity<>(users, HttpStatus.OK);
            return usrDto;
        } else{
            throw new DataException("No se encontro el usuario con el id:"+id);
        }
    }
    
    @Transactional
    public boolean createUser(UsuarioPagoDTO usrPg){
        ArrayList<String> usuarios=new ArrayList<>();
        Optional<UserPersonal> usrOpc = usrPrsRep.findById(usrPg.getUser_id());
        Optional<Plan> plOpc = plRep.findById(usrPg.getId_plan());
        if (!usrPg.getUsers().isEmpty()&usrOpc.isPresent() & plOpc.isPresent()) {
        //if (!usrPg.getUsers().isEmpty() & plOpc.isPresent()) {
            for (UserListDTO usr : usrPg.getUsers()) {
                UserCliente user = new UserCliente(plOpc.get(), usr.getNombre(), usr.getApellidos(),
                        usr.getTelefono(), usr.getEdad(), usr.getCorreo(),
                        usr.getFoto(), usr.getQr());
                usrClRep.save(user);
                usuarios.add(user.getId_usuario());
            }
            Pago pay= new Pago(usuarios, usrPg.getF_inicio(),usrPg.getF_fin(), usrPg.getFechaPago(),
                    usrPg.getComprobante(), plOpc.get().getCost(), usrOpc.get(), plOpc.get());
            pgRep.save(pay);
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean createPersonal(UserListDTO usrPg, int id){
        //Optional<Horario> hrrOpc=hroRep.findById(id);
        UserPersonal user = new UserPersonal(null, usrPg.getNombre(), usrPg.getApellidos(),
                usrPg.getTelefono(), usrPg.getEdad(), usrPg.getCorreo(),
                usrPg.getFoto(), usrPg.getQr());
        usrPrsRep.save(user);
        return true;
    }
    
}
