package com.example.demo.controller;

import com.example.demo.dto.ItemMagicoDTO;
import com.example.demo.model.ItemMagico;
import com.example.demo.service.RpgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemMagicoController {
    @Autowired
    private RpgService service;

    @PostMapping
    public ResponseEntity<ItemMagico> cadastrar(@RequestBody ItemMagicoDTO dto) {
        ItemMagico item = service.cadastrarItemMagico(dto);
        return ResponseEntity.ok(item);
    }

    @GetMapping
    public ResponseEntity<List<ItemMagico>> listar() {
        return ResponseEntity.ok(service.listarItensMagicos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemMagico> buscar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.buscarItemMagico(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}