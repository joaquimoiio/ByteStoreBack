package com.projetobackend.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ItemPedidoRecordDto(
        @NotNull Integer cdItemPedido,
        @NotNull @Min(1) Integer qtPedido,
        @NotNull BigDecimal vlUnitario) {
}