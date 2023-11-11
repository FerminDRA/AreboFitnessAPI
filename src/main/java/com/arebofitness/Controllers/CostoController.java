package com.arebofitness.Controllers;

import com.arebofitness.Models.Costo;
import com.arebofitness.Repositories.CostoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/costos")
public class CostoController {
    private final CostoRepository costoRepository;

    @Autowired
    public CostoController(CostoRepository costoRepository) {
        this.costoRepository = costoRepository;
    }

    // Obtener todos los costos
    @GetMapping("/")
    public ResponseEntity<List<Costo>> getAllCostos() {
        List<Costo> costos = costoRepository.findAll();
        return new ResponseEntity<>(costos, HttpStatus.OK);
    }

    // Obtener un costo por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Costo> getCostoById(@PathVariable int id) {
        Optional<Costo> optionalCosto = costoRepository.findById(id);
        if (optionalCosto.isPresent()) {
            return new ResponseEntity<>(optionalCosto.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo costo
    @PostMapping("/")
    public ResponseEntity<Costo> createCosto(@RequestBody Costo costo) {
        Costo nuevoCosto = costoRepository.save(costo);
        return new ResponseEntity<>(nuevoCosto, HttpStatus.CREATED);
    }

    // Actualizar un costo existente
    @PutMapping("/{id}")
    public ResponseEntity<Costo> updateCosto(@PathVariable int id, @RequestBody Costo costo) {
        if (costoRepository.existsById(id)) {
            costo.setId_costo(id);
            Costo costoActualizado = costoRepository.save(costo);
            return new ResponseEntity<>(costoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un costo por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCosto(@PathVariable int id) {
        if (costoRepository.existsById(id)) {
            costoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
