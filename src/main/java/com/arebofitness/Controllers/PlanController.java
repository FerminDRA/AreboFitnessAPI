package com.arebofitness.Controllers;

import com.arebofitness.Models.Plan;
import com.arebofitness.Repositories.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/arebofitness/planes")
public class PlanController {
    private final PlanRepository planRepository;

    @Autowired
    public PlanController(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    // Obtener todos los planes
    @GetMapping("/")
    public ResponseEntity<List<Plan>> getAllPlanes() {
        List<Plan> planes = planRepository.findAll();
        return new ResponseEntity<>(planes, HttpStatus.OK);
    }

    // Obtener un plan por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Plan> getPlanById(@PathVariable int id) {
        Optional<Plan> optionalPlan = planRepository.findById(id);
        if (optionalPlan.isPresent()) {
            return new ResponseEntity<>(optionalPlan.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Crear un nuevo plan
    @PostMapping("/")
    public ResponseEntity<Plan> createPlan(@RequestBody Plan plan) {
        Plan nuevoPlan = planRepository.save(plan);
        return new ResponseEntity<>(nuevoPlan, HttpStatus.CREATED);
    }

    // Actualizar un plan existente
    @PutMapping("/{id}")
    public ResponseEntity<Plan> updatePlan(@PathVariable int id, @RequestBody Plan plan) {
        if (planRepository.existsById(id)) {
            plan.setId_plan(id);
            Plan planActualizado = planRepository.save(plan);
            return new ResponseEntity<>(planActualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Eliminar un plan por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlan(@PathVariable int id) {
        if (planRepository.existsById(id)) {
            planRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
