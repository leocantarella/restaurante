package com.estudos.restaurante.service;

import org.springframework.stereotype.Service;
import com.estudos.restaurante.model.Item;
import com.estudos.restaurante.model.Pedido;
import com.estudos.restaurante.repository.ItemRepository;
import com.estudos.restaurante.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    // Listar todos os itens
    public List<Item> listarTodos() {
        return itemRepository.findAll();
    }

    // Buscar por id
    public Optional<Item> buscarPorId(Long id) {
        return itemRepository.findById(id);
    }

    // Criar um novo item
    public Item criarItem(Item item) {
        return itemRepository.save(item);
    }

    // Atualizar item
    public Optional<Item> atualizarItem(Long id, Item itemAtualizado) {
        return itemRepository.findById(id).map(i -> {
            i.setNome(itemAtualizado.getNome());
            i.setTipo(itemAtualizado.getTipo());
            return itemRepository.save(i);
        });
    }

    // Deletar item
    public boolean deletarItem(Long id) {
        if (itemRepository.existsById(id)) {
            itemRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
