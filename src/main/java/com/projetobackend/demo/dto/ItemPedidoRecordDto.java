package com.projetobackend.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemPedidoRecordDto(
        @NotNull Integer produtoId,
        @NotNull @Min(1) Integer quantidade,
        @NotNull BigDecimal precoUnitario) {
}