package com.arebofitness.Controllers;

import com.arebofitness.Models.Admin;
import com.arebofitness.Repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/admins")
public class AdminController {
    private final AdminRepository adminRepository;

    @Autowired
    public AdminController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    // Obtener todos los administradores
    @GetMapping("/")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminRepository.findAll();
        return new ResponseEntity<>(admins, HttpStatus.OK);
    }

    // Obtener un administrador por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Admin> getAdminById(@PathVariable int id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            return new ResponseEntity<>(optionalAdmin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo administrador
    @PostMapping("/")
    public ResponseEntity<Admin> createAdmin(@RequestBody Admin admin) {
        Admin nuevoAdmin = adminRepository.save(admin);
        return new ResponseEntity<>(nuevoAdmin, HttpStatus.CREATED);
    }

    // Actualizar un administrador existente
    @PutMapping("/{id}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable int id, @RequestBody Admin admin) {
        if (adminRepository.existsById(id)) {
            admin.setId_admin(id);
            Admin adminActualizado = adminRepository.save(admin);
            return new ResponseEntity<>(adminActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un administrador por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdmin(@PathVariable int id) {
        if (adminRepository.existsById(id)) {
            adminRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
