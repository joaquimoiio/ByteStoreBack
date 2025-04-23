package com.projetobackend.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;

public record ProdutoRecordDto(
        @NotBlank String nome,
        @NotBlank String descricao,
        @NotNull BigDecimal precoAntigo,
        @NotNull BigDecimal precoAtual,
        @NotNull Integer estoque,
        @NotBlank String categoria,
        @NotBlank String imagemPrincipal,
        List<String> imagensGaleria,
        @NotNull Boolean destaque) {
}