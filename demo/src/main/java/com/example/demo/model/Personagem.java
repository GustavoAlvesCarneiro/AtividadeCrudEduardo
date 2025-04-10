package com.example.demo.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personagem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;
    private String nomeAventureiro;

    @Enumerated(EnumType.STRING)
    private Classe classe;

    private int level;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "personagem_id")
    private List<ItemMagico> itensMagicos = new ArrayList<>();

    private int forcaBase;
    private int defesaBase;

    public Personagem() {}

    public Personagem(String nome, String nomeAventureiro, Classe classe, int level, int forcaBase, int defesaBase) {
        if (forcaBase + defesaBase > 10 || forcaBase < 0 || defesaBase < 0) {
            throw new IllegalArgumentException("força mais defesa não pode passar de 10 ou ser negativo");
        }
        this.nome = nome;
        this.nomeAventureiro = nomeAventureiro;
        this.classe = classe;
        this.level = level;
        this.forcaBase = forcaBase;
        this.defesaBase = defesaBase;
    }

    public void adicionarItem(ItemMagico item) {

        boolean itemJaExiste = itensMagicos.stream().anyMatch(i -> i.getId() == item.getId());
        if (itemJaExiste) {
            return;
        }

        if (item.getTipo() == TipoItem.AMULETO) {
            long qtdAmuletos = itensMagicos.stream().filter(i -> i.getTipo() == TipoItem.AMULETO).count();
            if (qtdAmuletos >= 1) {
                throw new IllegalStateException("o personagem já tem um amuleto");
            }
        }

        itensMagicos.add(item);
    }

    public void removerItem(int itemId) {
        itensMagicos.removeIf(item -> item.getId() == itemId);
    }

    public int getForcaTotal() {
        return forcaBase + itensMagicos.stream().mapToInt(ItemMagico::getForca).sum();
    }

    public int getDefesaTotal() {
        return defesaBase + itensMagicos.stream().mapToInt(ItemMagico::getDefesa).sum();
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getNomeAventureiro() { return nomeAventureiro; }
    public void setNomeAventureiro(String nomeAventureiro) { this.nomeAventureiro = nomeAventureiro; }
    public Classe getClasse() { return classe; }
    public void setClasse(Classe classe) { this.classe = classe; }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; }
    public List<ItemMagico> getItensMagicos() { return itensMagicos; }
    public int getForcaBase() { return forcaBase; }
    public void setForcaBase(int forcaBase) { this.forcaBase = forcaBase; }
    public int getDefesaBase() { return defesaBase; }
    public void setDefesaBase(int defesaBase) { this.defesaBase = defesaBase; }
}