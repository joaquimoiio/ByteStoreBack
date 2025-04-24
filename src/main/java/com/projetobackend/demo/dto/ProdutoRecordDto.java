
package com.projetobackend.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRecordDto(
        @NotBlank String nmProduto,
        @NotBlank String dsProduto,
        @NotNull BigDecimal precoAntigo,
        @NotNull BigDecimal vlProduto,
        @NotNull Integer estoque,
        @NotBlank String categoria,
        @NotBlank String imagemPrincipal,
        List<String> imagensGaleria,
        @NotNull Boolean destaque) {
}