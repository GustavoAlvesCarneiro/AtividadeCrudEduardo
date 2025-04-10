package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ItemMagico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoItem tipo;

    private int forca;
    private int defesa;

    public ItemMagico() {}

    public ItemMagico(String nome, TipoItem tipo, int forca, int defesa) {
        if (forca < 0 || defesa < 0 || forca > 10 || defesa > 10) {
            throw new IllegalArgumentException("força e defesa deve estar entre 0 e 10");
        }
        if (tipo == TipoItem.ARMA && defesa != 0) {
            throw new IllegalArgumentException("arma não pode ter defesa");
        }
        if (tipo == TipoItem.ARMADURA && forca != 0) {
            throw new IllegalArgumentException("armadura não pode ter força");
        }
        if (forca == 0 && defesa == 0) {
            throw new IllegalArgumentException("item não pode ter força e defesa zerado");
        }
        this.nome = nome;
        this.tipo = tipo;
        this.forca = forca;
        this.defesa = defesa;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public TipoItem getTipo() { return tipo; }
    public void setTipo(TipoItem tipo) { this.tipo = tipo; }
    public int getForca() { return forca; }
    public void setForca(int forca) { this.forca = forca; }
    public int getDefesa() { return defesa; }
    public void setDefesa(int defesa) { this.defesa = defesa; }
}