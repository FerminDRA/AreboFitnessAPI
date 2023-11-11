package com.arebofitness.Controllers;

import com.arebofitness.Models.Horario;
import com.arebofitness.Repositories.HorarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/horarios")
public class HorarioController {
    private final HorarioRepository horarioRepository;

    @Autowired
    public HorarioController(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    // Obtener todos los horarios
    @GetMapping("/")
    public ResponseEntity<List<Horario>> getAllHorarios() {
        List<Horario> horarios = horarioRepository.findAll();
        return new ResponseEntity<>(horarios, HttpStatus.OK);
    }

    // Obtener un horario por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Horario> getHorarioById(@PathVariable int id) {
        Optional<Horario> optionalHorario = horarioRepository.findById(id);
        if (optionalHorario.isPresent()) {
            return new ResponseEntity<>(optionalHorario.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo horario
    @PostMapping("/")
    public ResponseEntity<Horario> createHorario(@RequestBody Horario horario) {
        Horario nuevoHorario = horarioRepository.save(horario);
        return new ResponseEntity<>(nuevoHorario, HttpStatus.CREATED);
    }

    // Actualizar un horario existente
    @PutMapping("/{id}")
    public ResponseEntity<Horario> updateHorario(@PathVariable int id, @RequestBody Horario horario) {
        if (horarioRepository.existsById(id)) {
            horario.setId_horario(id);
            Horario horarioActualizado = horarioRepository.save(horario);
            return new ResponseEntity<>(horarioActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un horario por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHorario(@PathVariable int id) {
        if (horarioRepository.existsById(id)) {
            horarioRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
