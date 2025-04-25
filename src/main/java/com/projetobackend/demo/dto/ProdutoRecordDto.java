package com.projetobackend.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRecordDto(
        @NotBlank String nmProduto,
        @NotBlank String dsProduto,
        @NotNull BigDecimal vlAntigo,
        @NotNull BigDecimal vlProduto,
        @NotNull Integer dsEstoque,
        @NotBlank String dsCategoria,
        @NotBlank String imagemPrincipal,
        String imagensGaleria,
        @NotNull Boolean destaque) {
}