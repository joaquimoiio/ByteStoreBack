package com.projetobackend.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record SenhaRecordDto(
        @NotBlank String senhaAtual,
        @NotBlank String novaSenha)  {
}