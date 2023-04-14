package com.example.postman_test.controller;

import java.math.BigDecimal;
import java.util.Optional;

import com.example.postman_test.entity.Pokaznuk;
import com.example.postman_test.repository.PokaznukRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokaznuk")
public class PokaznukController {
    private final PokaznukRepository pokaznukRepository;

    public PokaznukController(PokaznukRepository pokaznukRepository) {
        this.pokaznukRepository = pokaznukRepository;
    }

    @GetMapping
    public ResponseEntity<List<Pokaznuk>> getAllPokaznuk() {
        try {
            List<Pokaznuk> pokaznuks = pokaznukRepository.findAll();

            if (pokaznuks.isEmpty()) {
                return new ResponseEntity<>(pokaznuks, HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(pokaznuks, HttpStatus.OK);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pokaznuk> getPokaznukById (@PathVariable("id") Long id) {

        Optional<Pokaznuk> pokaznuk = pokaznukRepository.findById(id);

        if (pokaznuk.isPresent()) {
            return new ResponseEntity<>(
                    pokaznuk.get(),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Pokaznuk> saveNewPokaznuk (@RequestBody Pokaznuk pokaznuk) {
        try {
            Pokaznuk pokaznuk1 = pokaznukRepository.save(pokaznuk);
            return new ResponseEntity<>(pokaznuk1,  HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokaznuk> updatePokaznuk (@PathVariable("id") Long id,
                                                   @RequestBody Pokaznuk student) {

        Optional<Pokaznuk> pokaznukOptional = pokaznukRepository.findById(id);

        try {
            if (pokaznukOptional.isPresent()) {
                Pokaznuk pokaznuk1 = pokaznukOptional.get();

                pokaznuk1.setName("Gas");
                pokaznuk1.setTarif(BigDecimal.valueOf(12.24));
                pokaznuk1.setAttribute("square meters");

                Pokaznuk pokaznuk2 = pokaznukRepository.save(pokaznuk1);

                return new ResponseEntity<>(pokaznuk2, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById (@PathVariable("id") Long id) {
        try {
            pokaznukRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteAllPokaznuk() {
        try {
            pokaznukRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception exception) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
