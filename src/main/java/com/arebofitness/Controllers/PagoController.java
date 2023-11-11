package com.arebofitness.Controllers;

import com.arebofitness.Models.Pago;
import com.arebofitness.Repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/pagos")
public class PagoController {
    private final PagoRepository pagoRepository;

    @Autowired
    public PagoController(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    // Obtener todos los pagos
    @GetMapping("/")
    public ResponseEntity<List<Pago>> getAllPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    // Obtener un pago por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Pago> getPagoById(@PathVariable int id) {
        Optional<Pago> optionalPago = pagoRepository.findById(id);
        if (optionalPago.isPresent()) {
            return new ResponseEntity<>(optionalPago.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo pago
    @PostMapping("/")
    public ResponseEntity<Pago> createPago(@RequestBody Pago pago) {
        Pago nuevoPago = pagoRepository.save(pago);
        return new ResponseEntity<>(nuevoPago, HttpStatus.CREATED);
    }

    // Actualizar un pago existente
    @PutMapping("/{id}")
    public ResponseEntity<Pago> updatePago(@PathVariable int id, @RequestBody Pago pago) {
        if (pagoRepository.existsById(id)) {
            pago.setId_pago(id);
            Pago pagoActualizado = pagoRepository.save(pago);
            return new ResponseEntity<>(pagoActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un pago por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePago(@PathVariable int id) {
        if (pagoRepository.existsById(id)) {
            pagoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
