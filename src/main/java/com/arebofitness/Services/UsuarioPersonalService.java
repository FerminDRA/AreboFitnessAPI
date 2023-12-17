/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Services;

import com.arebofitness.DTOs.UserListDTO;
import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Models.Horario;
import com.arebofitness.Models.UserPersonal;
import com.arebofitness.Repositories.HorarioRepository;
import com.arebofitness.Repositories.UsuarioPsnlRepository;
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
public class UsuarioPersonalService{
    @Autowired
    UsuarioPsnlRepository usrPrsl;
    @Autowired
    HorarioRepository hroRep;

    public boolean createPersonal(UserListDTO usrPg, int id) {
        Optional<Horario> hrrOpc=hroRep.findById(id);
        if(hrrOpc.isPresent()){
            Horario hr=hrrOpc.get();
            UserPersonal user = new UserPersonal(hr, usrPg.getNombre(), usrPg.getApellidos(),
                usrPg.getTelefono(), usrPg.getEdad(), usrPg.getCorreo(),
                usrPg.getFoto(), usrPg.getQr());
            usrPrsl.save(user);
            return true;
        }
        return false;
        
    }

    public void deletePersonal(String id) {
        Optional<UserPersonal> opc=usrPrsl.findById(id);
        if(opc.isPresent()){
            usrPrsl.deleteById(id);
        }
        else{
            throw new DataException("No se encontro personal con el id: "+id);
        }
    }

    public void updatePersonal() {
    }

    public UserListDTO getPersonalbyID(String id) {
        //List<UserListDTO> personal= new ArrayList<>();
        Optional<UserPersonal> opcPers=usrPrsl.findById(id);
        if(opcPers.isPresent()){
            UserPersonal psr=opcPers.get();
            UserListDTO personal= new UserListDTO(psr.getName(), psr.getLastname(), psr.getAge(),
                    psr.getPhone(), psr.getEmail(), psr.getFoto(),
                    psr.getQr(), psr.getHorario().getNombre());
            return personal;
        }
        else{
            throw new DataException("No hay Personal con el ID:"+id);
        }
    }

    public List<UserListDTO> getAllPersonal() {
        List<UserPersonal> usrPrs=usrPrsl.findAll();
        if(!usrPrs.isEmpty()){
            List<UserListDTO> personal = new ArrayList<>();
            for (UserPersonal psr : usrPrs) {
                UserListDTO user= new UserListDTO(psr.getName(), psr.getLastname(), psr.getAge(),
                    psr.getPhone(), psr.getEmail(), psr.getFoto(),
                    psr.getQr(), psr.getHorario().getNombre());
                personal.add(user);
            }
            return personal;
        }
        else{
            throw new DataException("No hay Personal");
        }
    }
    
}
