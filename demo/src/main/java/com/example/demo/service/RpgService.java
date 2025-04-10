package com.example.demo.service;

import com.example.demo.dto.PersonagemDTO;
import com.example.demo.dto.ItemMagicoDTO;
import com.example.demo.model.ItemMagico;
import com.example.demo.model.Personagem;
import com.example.demo.model.TipoItem;
import com.example.demo.repository.ItemMagicoRepository;
import com.example.demo.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RpgService {
    @Autowired
    private PersonagemRepository personagemRepo;

    @Autowired
    private ItemMagicoRepository itemRepo;
    public Personagem cadastrarPersonagem(PersonagemDTO dto) {
        Personagem p = new Personagem(dto.getNome(), dto.getNomeAventureiro(), dto.getClasse(),
                dto.getLevel(), dto.getForca(), dto.getDefesa());
        return personagemRepo.save(p);
    }

    public List<Personagem> listarPersonagens() {
        return personagemRepo.findAll();
    }

    public Personagem buscarPersonagem(int id) {
        return personagemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("personagem não encontrado"));
    }

    public Personagem atualizarNomeAventureiro(int id, PersonagemDTO dto) {
        Personagem p = buscarPersonagem(id);
        p.setNomeAventureiro(dto.getNomeAventureiro());
        return personagemRepo.save(p);
    }

    public void removerPersonagem(int id) {
        personagemRepo.deleteById(id);
    }

    public ItemMagico cadastrarItemMagico(ItemMagicoDTO dto) {
        ItemMagico item = new ItemMagico(dto.getNome(), dto.getTipo(), dto.getForca(), dto.getDefesa());
        return itemRepo.save(item);
    }

    public List<ItemMagico> listarItensMagicos() {
        return itemRepo.findAll();
    }
    public ItemMagico buscarItemMagico(int id) {
        return itemRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("item não encontrado"));
    }

    public Personagem adicionarItemAoPersonagem(int personagemId, int itemId) {
        Personagem p = buscarPersonagem(personagemId);
        ItemMagico i = buscarItemMagico(itemId);
        p.adicionarItem(i);
        return personagemRepo.save(p);
    }

    public List<ItemMagico> listarItensPorPersonagem(int personagemId) {
        return buscarPersonagem(personagemId).getItensMagicos();
    }

    public Personagem removerItemDoPersonagem(int personagemId, int itemId) {
        Personagem p = buscarPersonagem(personagemId);
        p.removerItem(itemId);
        return personagemRepo.save(p);
    }
    public ItemMagico buscarAmuletoDoPersonagem(int personagemId) {
        Personagem p = buscarPersonagem(personagemId);
        return p.getItensMagicos().stream()
                .filter(i -> i.getTipo() == TipoItem.AMULETO)
                .findFirst()
                .orElse(null);
    }

}