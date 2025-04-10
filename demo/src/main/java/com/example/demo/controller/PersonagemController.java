package com.example.demo.controller;

import com.example.demo.dto.PersonagemDTO;
import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.service.RpgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personagens")
public class PersonagemController {
    @Autowired
    private RpgService service;

    @PostMapping
    public ResponseEntity<Personagem> cadastrar(@RequestBody PersonagemDTO dto) {
        Personagem p = service.cadastrarPersonagem(dto);
        return ResponseEntity.ok(p);
    }

    @GetMapping
    public ResponseEntity<List<Personagem>> listar() {
        return ResponseEntity.ok(service.listarPersonagens());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> buscar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.buscarPersonagem(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/{id}/nome-aventureiro")
    public ResponseEntity<Personagem> atualizarNome(@PathVariable int id, @RequestBody PersonagemDTO dto) {
        try {
            return ResponseEntity.ok(service.atualizarNomeAventureiro(id, dto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable int id) {
        try {
            service.removerPersonagem(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).build();
        }
    }

    @PostMapping("/{id}/itens/{itemId}")
    public ResponseEntity<Personagem> adicionarItem(@PathVariable int id, @PathVariable int itemId) {
        try {
            return ResponseEntity.ok(service.adicionarItemAoPersonagem(id, itemId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<ItemMagico>> listarItens(@PathVariable int id) {
        try {
            return ResponseEntity.ok(service.listarItensPorPersonagem(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}/itens/{itemId}")
    public ResponseEntity<Personagem> removerItem(@PathVariable int id, @PathVariable int itemId) {
        try {
            return ResponseEntity.ok(service.removerItemDoPersonagem(id, itemId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @GetMapping("/{id}/amuleto")
    public ResponseEntity<ItemMagico> buscarAmuleto(@PathVariable int id) {
        try {
            ItemMagico amuleto = service.buscarAmuletoDoPersonagem(id);
            if (amuleto == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(amuleto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }
}