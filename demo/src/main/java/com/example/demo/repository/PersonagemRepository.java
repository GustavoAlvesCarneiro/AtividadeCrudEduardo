package com.example.demo.repository;

import com.example.demo.model.Personagem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {
}