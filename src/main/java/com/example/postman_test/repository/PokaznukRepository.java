package com.example.postman_test.repository;

import com.example.postman_test.entity.Pokaznuk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokaznukRepository extends JpaRepository<Pokaznuk, Long> {

}
