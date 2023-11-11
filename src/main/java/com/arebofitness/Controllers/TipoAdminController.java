package com.arebofitness.Controllers;

import com.arebofitness.Models.TipoAdmin;
import com.arebofitness.Repositories.TipoAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/tipos-admins")
public class TipoAdminController {
    private final TipoAdminRepository tipoAdminRepository;

    @Autowired
    public TipoAdminController(TipoAdminRepository tipoAdminRepository) {
        this.tipoAdminRepository = tipoAdminRepository;
    }

    // Obtener todos los tipos de administradores
    @GetMapping("/")
    public ResponseEntity<List<TipoAdmin>> getAllTiposAdmin() {
        List<TipoAdmin> tiposAdmin = tipoAdminRepository.findAll();
        return new ResponseEntity<>(tiposAdmin, HttpStatus.OK);
    }

    // Obtener un tipo de administrador por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoAdmin> getTipoAdminById(@PathVariable int id) {
        Optional<TipoAdmin> optionalTipoAdmin = tipoAdminRepository.findById(id);
        if (optionalTipoAdmin.isPresent()) {
            return new ResponseEntity<>(optionalTipoAdmin.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo tipo de administrador
    @PostMapping("/")
    public ResponseEntity<TipoAdmin> createTipoAdmin(@RequestBody TipoAdmin tipoAdmin) {
        TipoAdmin nuevoTipoAdmin = tipoAdminRepository.save(tipoAdmin);
        return new ResponseEntity<>(nuevoTipoAdmin, HttpStatus.CREATED);
    }

    // Actualizar un tipo de administrador existente
    @PutMapping("/{id}")
    public ResponseEntity<TipoAdmin> updateTipoAdmin(@PathVariable int id, @RequestBody TipoAdmin tipoAdmin) {
        if (tipoAdminRepository.existsById(id)) {
            tipoAdmin.setId_tipo(id);
            TipoAdmin tipoAdminActualizado = tipoAdminRepository.save(tipoAdmin);
            return new ResponseEntity<>(tipoAdminActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un tipo de administrador por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoAdmin(@PathVariable int id) {
        if (tipoAdminRepository.existsById(id)) {
            tipoAdminRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
