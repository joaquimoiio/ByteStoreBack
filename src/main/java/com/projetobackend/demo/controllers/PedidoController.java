package com.projetobackend.demo.controllers;

import com.projetobackend.demo.dto.ItemPedidoRecordDto;
import com.projetobackend.demo.dto.PedidoRecordDto;
import com.projetobackend.demo.models.ClientModel;
import com.projetobackend.demo.models.ItemPedidoModel;
import com.projetobackend.demo.models.PedidoModel;
import com.projetobackend.demo.models.ProdutoModel;
import com.projetobackend.demo.repository.ClientRepository;
import com.projetobackend.demo.repository.PedidoRepository;
import com.projetobackend.demo.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<PedidoModel>> getAllPedidos() {
        return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePedido(@PathVariable(value = "id") int id) {
        Optional<PedidoModel> pedido = pedidoRepository.findById(id);
        if (pedido.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pedido.get());
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<PedidoModel>> getPedidosByCliente(@PathVariable(value = "clienteId") int clienteId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pedidoRepository.findByClienteIdOrderByDataHoraDesc(clienteId));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Object> addPedido(@RequestBody @Valid PedidoRecordDto pedidoRecordDto) {
        // Verificar se o cliente existe
        Optional<ClientModel> clienteOpt = clientRepository.findById(pedidoRecordDto.clienteId());
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        // Criar novo pedido
        PedidoModel pedido = new PedidoModel();
        pedido.setCliente(clienteOpt.get());
        pedido.setCepEntrega(pedidoRecordDto.cepEntrega());
        pedido.setEnderecoEntrega(pedidoRecordDto.enderecoEntrega());

        // Criar itens do pedido
        List<ItemPedidoModel> itens = new ArrayList<>();
        for (ItemPedidoRecordDto itemDto : pedidoRecordDto.itens()) {
            // Verificar se o produto existe
            Optional<ProdutoModel> produtoOpt = produtoRepository.findById(itemDto.produtoId());
            if (produtoOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Produto não encontrado: ID " + itemDto.produtoId());
            }

            ProdutoModel produto = produtoOpt.get();

            // Verificar estoque
            if (produto.getEstoque() < itemDto.quantidade()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Estoque insuficiente para o produto: " + produto.getNome());
            }

            // Atualizar estoque
            produto.setEstoque(produto.getEstoque() - itemDto.quantidade());
            produtoRepository.save(produto);

            // Criar item do pedido
            ItemPedidoModel item = new ItemPedidoModel();
            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemDto.quantidade());
            item.setPrecoUnitario(itemDto.precoUnitario());

            itens.add(item);
        }

        pedido.setItens(itens);
        pedido.calcularValorTotal();

        // Salvar pedido
        PedidoModel savedPedido = pedidoRepository.save(pedido);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedPedido);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Object> updatePedidoStatus(@PathVariable(value = "id") int id,
                                                     @RequestParam String novoStatus) {
        Optional<PedidoModel> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        PedidoModel pedido = pedidoOpt.get();
        pedido.setStatus(novoStatus);

        return ResponseEntity.status(HttpStatus.OK).body(pedidoRepository.save(pedido));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> cancelPedido(@PathVariable(value = "id") int id) {
        Optional<PedidoModel> pedidoOpt = pedidoRepository.findById(id);
        if (pedidoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pedido não encontrado");
        }

        PedidoModel pedido = pedidoOpt.get();

        // Se o pedido já estiver cancelado ou entregue, não permitir cancelamento
        if ("Cancelado".equals(pedido.getStatus()) || "Entregue".equals(pedido.getStatus())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Não é possível cancelar um pedido com status: " + pedido.getStatus());
        }

        // Devolver itens ao estoque
        for (ItemPedidoModel item : pedido.getItens()) {
            ProdutoModel produto = item.getProduto();
            produto.setEstoque(produto.getEstoque() + item.getQuantidade());
            produtoRepository.save(produto);
        }

        // Cancelar pedido
        pedido.setStatus("Cancelado");
        pedidoRepository.save(pedido);

        return ResponseEntity.status(HttpStatus.OK).body("Pedido cancelado com sucesso");
    }
}