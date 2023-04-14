package com.example.postman_test.repository;

import com.example.postman_test.entity.Poslyga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoslygaRepository extends JpaRepository<Poslyga, Long> {
}
