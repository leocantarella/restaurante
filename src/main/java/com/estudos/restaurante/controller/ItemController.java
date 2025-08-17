package com.estudos.restaurante.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.estudos.restaurante.model.Item;
import com.estudos.restaurante.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/itens")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // Listar todos
    @GetMapping
    public ResponseEntity<List<Item>> listarTodos() {
        return ResponseEntity.ok(itemService.listarTodos());
    }

    // Buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Item> buscarPorId(@PathVariable Long id) {
        return itemService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Criar item
    @PostMapping
    public ResponseEntity<Item> criarItem(@RequestBody Item item) {
        Item criado = itemService.criarItem(item);
        return ResponseEntity.created(URI.create("/itens/" + criado.getId()))
                .body(criado);
    }

    // Atualizar item
    @PutMapping("/{id}")
    public ResponseEntity<Item> atualizarItem(@PathVariable Long id, @RequestBody Item itemAtualizado) {
        return itemService.atualizarItem(id, itemAtualizado)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Deletar item
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarItem(@PathVariable Long id) {
        boolean deletado = itemService.deletarItem(id);
        if (deletado) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build();   // 404 Not Found
        }
    }
}
