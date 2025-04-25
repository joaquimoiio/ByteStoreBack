package com.projetobackend.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record PedidoRecordDto(
        @NotNull Integer clienteId,
        @NotEmpty List<ItemPedidoRecordDto> itens,
        String cepEntrega,
        String enderecoEntrega) {
}