package com.arebofitness.Controllers;

import com.arebofitness.Models.RegistroEntrada;
import com.arebofitness.Repositories.RegistroEntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/registros-entrada")
public class RegistroEntradaController {
    private final RegistroEntradaRepository registroEntradaRepository;

    @Autowired
    public RegistroEntradaController(RegistroEntradaRepository registroEntradaRepository) {
        this.registroEntradaRepository = registroEntradaRepository;
    }

    // Obtener todos los registros de entrada
    @GetMapping("/")
    public ResponseEntity<List<RegistroEntrada>> getAllRegistrosEntrada() {
        List<RegistroEntrada> registrosEntrada = registroEntradaRepository.findAll();
        return new ResponseEntity<>(registrosEntrada, HttpStatus.OK);
    }

    // Obtener un registro de entrada por su ID
    @GetMapping("/{id}")
    public ResponseEntity<RegistroEntrada> getRegistroEntradaById(@PathVariable int id) {
        Optional<RegistroEntrada> optionalRegistroEntrada = registroEntradaRepository.findById(id);
        if (optionalRegistroEntrada.isPresent()) {
            return new ResponseEntity<>(optionalRegistroEntrada.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo registro de entrada
    @PostMapping("/")
    public ResponseEntity<RegistroEntrada> createRegistroEntrada(@RequestBody RegistroEntrada registroEntrada) {
        RegistroEntrada nuevoRegistroEntrada = registroEntradaRepository.save(registroEntrada);
        return new ResponseEntity<>(nuevoRegistroEntrada, HttpStatus.CREATED);
    }

    // Actualizar un registro de entrada existente
    @PutMapping("/{id}")
    public ResponseEntity<RegistroEntrada> updateRegistroEntrada(@PathVariable int id, @RequestBody RegistroEntrada registroEntrada) {
        if (registroEntradaRepository.existsById(id)) {
            registroEntrada.setId_registro(id);
            RegistroEntrada registroEntradaActualizado = registroEntradaRepository.save(registroEntrada);
            return new ResponseEntity<>(registroEntradaActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un registro de entrada por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegistroEntrada(@PathVariable int id) {
        if (registroEntradaRepository.existsById(id)) {
            registroEntradaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
