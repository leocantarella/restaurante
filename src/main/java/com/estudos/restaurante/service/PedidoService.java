package com.estudos.restaurante.service;

import com.estudos.restaurante.model.Item;
import com.estudos.restaurante.model.Pedido;
import com.estudos.restaurante.repository.ItemRepository;
import com.estudos.restaurante.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemRepository itemRepository;

    // Listar todos os pedidos
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    // Buscar pedido por ID
    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    // Criar pedido (verifica se o item existe)
    public Pedido criarPedido(Pedido pedido) {
        Long itemId = pedido.getItem().getId();
        Item item = itemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));
        pedido.setItem(item);
        return pedidoRepository.save(pedido);
    }

    // Atualizar pedido
    public Pedido atualizarPedido(Long id, Pedido pedidoAtualizado) {
        return pedidoRepository.findById(id).map(p -> {
            p.setQuantidade(pedidoAtualizado.getQuantidade());
            p.setMesa(pedidoAtualizado.getMesa());

            if (pedidoAtualizado.getItem() != null) {
                Item item = itemRepository.findById(pedidoAtualizado.getItem().getId())
                        .orElseThrow(() -> new RuntimeException("Item não encontrado"));
                p.setItem(item);
            }

            return pedidoRepository.save(p);
        }).orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    // Deletar pedido
    public void deletarPedido(Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new RuntimeException("Pedido não encontrado");
        }
        pedidoRepository.deleteById(id);
    }

}
