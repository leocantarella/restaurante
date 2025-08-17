package com.estudos.restaurante.controller;

import com.estudos.restaurante.model.Pedido;
import com.estudos.restaurante.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    // Criar pedido
    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
        Pedido criado = pedidoService.criarPedido(pedido);
        return ResponseEntity.ok(criado);
    }

    // Listar todos os pedidos
    @GetMapping
    public ResponseEntity<List<Pedido>> listarPedidos() {
        List<Pedido> pedidos = pedidoService.listarPedidos();
        if (pedidos.isEmpty()) {
            return ResponseEntity.noContent().build(); // retorna 204 se não houver pedidos
        }
        return ResponseEntity.ok(pedidos);
    }

    // Buscar pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPedido(@PathVariable Long id) {
        return pedidoService.buscarPorId(id)
                .map(ResponseEntity::ok) // retorna 200 OK se encontrado
                .orElse(ResponseEntity.notFound().build()); // retorna 404 se não encontrado
    }

    // Atualizar pedido
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedido) {
        try {
            Pedido atualizado = pedidoService.atualizarPedido(id, pedido);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Deletar pedido
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarPedido(@PathVariable Long id) {
        try {
            pedidoService.deletarPedido(id);
            return ResponseEntity.ok("Pedido deletado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
