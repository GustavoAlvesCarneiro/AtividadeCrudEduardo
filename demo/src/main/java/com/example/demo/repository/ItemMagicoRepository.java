package com.example.demo.repository;

import com.example.demo.model.ItemMagico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemMagicoRepository extends JpaRepository<ItemMagico, Integer> {
}