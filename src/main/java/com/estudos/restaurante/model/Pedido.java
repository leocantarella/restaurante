package com.estudos.restaurante.model;

import jakarta.persistence.*;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Item item;
    private int quantidade;
    private int mesa;

    public Pedido() {
    }

    public Pedido(Item item, int quantidade, int mesa) {
        this.item = item;
        this.quantidade = quantidade;
        this.mesa = mesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }
}
