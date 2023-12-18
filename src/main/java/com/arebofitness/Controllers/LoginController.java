/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.arebofitness.Controllers;

import com.arebofitness.Exceptions.DataException;
import com.arebofitness.Helpers.ApiResponseHelper;
import com.arebofitness.Models.CredentialsEntity;
import com.arebofitness.Repositories.CredentialsRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author fermin
 */
@RestController
@RequestMapping("/arebofitness/login")
public class LoginController {
    @Autowired
    CredentialsRepository crdRep;

    @PostMapping
    public ResponseEntity<Object> createHorario(@RequestBody CredentialsEntity loginRequest) {
        try {
            String email = loginRequest.getEmail();
            String password = loginRequest.getPassword();

            Optional<CredentialsEntity> personOptional = crdRep.findByUsernameAndPassword(email, password);
            if (personOptional.isPresent()) {
                // Inicio de sesión exitoso
                CredentialsEntity person = personOptional.get();
                return ApiResponseHelper.ok("Login exitoso", HttpStatus.ACCEPTED, person);
            } else {
                // Credenciales incorrectas
                return ApiResponseHelper.error("Login no autorizado", HttpStatus.UNAUTHORIZED, null);

            }
            
        } catch (DataException e) {
            return ApiResponseHelper.error("Error de peticion"+e.getMessage(), HttpStatus.NOT_ACCEPTABLE, null);
        }
    }
    
}
