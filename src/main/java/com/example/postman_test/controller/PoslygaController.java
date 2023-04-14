package com.example.postman_test.controller;

import java.util.Optional;

import com.example.postman_test.entity.Poslyga;
import com.example.postman_test.repository.PoslygaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/optional")
public class PoslygaController {
    private final PoslygaRepository poslygaRepository;

    public PoslygaController(PoslygaRepository poslygaRepository) {
        this.poslygaRepository = poslygaRepository;
    }

    @GetMapping
    public ResponseEntity<List<Poslyga>> getAllPoslyga() {
        try {
            List<Poslyga> poslygi = poslygaRepository.findAll();

            if (poslygi.isEmpty()) {
                return new ResponseEntity<>(poslygi, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(poslygi, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Poslyga> getPoslygaById(@PathVariable("id") Long id) {

        Optional<Poslyga> poslyga = poslygaRepository.findById(id);

        if (poslyga.isPresent()) {
            return new ResponseEntity<>(
                    poslyga.get(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Poslyga> saveNewPoslyga (@RequestBody Poslyga poslyga) {
        try {
            Poslyga poslyga1 = poslygaRepository.save(poslyga);
            return new ResponseEntity<>(poslyga1,  HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Poslyga> updatePoslyga (@PathVariable("id") Long id,
                                                  @RequestBody Poslyga poslyga) {

        Optional<Poslyga> poslygas = poslygaRepository.findById(id);

        try {
            if (poslygas.isPresent()) {
                Poslyga poslyga1 = poslygas.get();

                poslyga1.setName("dota");
                poslyga1.setDescription("dota2");

                Poslyga poslyga2 = poslygaRepository.save(poslyga1);

                return new ResponseEntity<>(poslyga1, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePoslygaById (@PathVariable("id") Long id) {
        try {
            poslygaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPoslyga() {
        try {
            poslygaRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
