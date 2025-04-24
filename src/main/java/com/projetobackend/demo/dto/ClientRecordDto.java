package com.projetobackend.demo.dto;

import jakarta.validation.constraints.NotBlank;

public record ClientRecordDto(
        @NotBlank String nmCliente,
        @NotBlank String nuCpf,
        @NotBlank String dsEmail,
        @NotBlank String dtNasc,
        @NotBlank String nuTelefone,
        @NotBlank String dsSenha)  {
}