package com.arebofitness.Controllers;

import com.arebofitness.Models.TipoUsuario;
import com.arebofitness.Repositories.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/tipos-usuarios")
public class TipoUsuarioController {
    private final TipoUsuarioRepository tipoUsuarioRepository;

    @Autowired
    public TipoUsuarioController(TipoUsuarioRepository tipoUsuarioRepository) {
        this.tipoUsuarioRepository = tipoUsuarioRepository;
    }

    // Obtener todos los tipos de usuarios
    @GetMapping("/")
    public ResponseEntity<List<TipoUsuario>> getAllTiposUsuarios() {
        List<TipoUsuario> tiposUsuarios = tipoUsuarioRepository.findAll();
        return new ResponseEntity<>(tiposUsuarios, HttpStatus.OK);
    }

    // Obtener un tipo de usuario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TipoUsuario> getTipoUsuarioById(@PathVariable int id) {
        Optional<TipoUsuario> optionalTipoUsuario = tipoUsuarioRepository.findById(id);
        if (optionalTipoUsuario.isPresent()) {
            return new ResponseEntity<>(optionalTipoUsuario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo tipo de usuario
    @PostMapping("/")
    public ResponseEntity<TipoUsuario> createTipoUsuario(@RequestBody TipoUsuario tipoUsuario) {
        TipoUsuario nuevoTipoUsuario = tipoUsuarioRepository.save(tipoUsuario);
        return new ResponseEntity<>(nuevoTipoUsuario, HttpStatus.CREATED);
    }

    // Actualizar un tipo de usuario existente
    @PutMapping("/{id}")
    public ResponseEntity<TipoUsuario> updateTipoUsuario(@PathVariable int id, @RequestBody TipoUsuario tipoUsuario) {
        if (tipoUsuarioRepository.existsById(id)) {
            tipoUsuario.setId_tipo(id);
            TipoUsuario tipoUsuarioActualizado = tipoUsuarioRepository.save(tipoUsuario);
            return new ResponseEntity<>(tipoUsuarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un tipo de usuario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipoUsuario(@PathVariable int id) {
        if (tipoUsuarioRepository.existsById(id)) {
            tipoUsuarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
