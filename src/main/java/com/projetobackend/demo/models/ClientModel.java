package com.projetobackend.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TBCLIENT")
public class ClientModel implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdCliente;

    @Column(nullable = false)
    private String nmCliente;

    @Column(nullable = false)
    private String nuCpf;

    @Column(nullable = false)
    private String dsEmail;

    @Column(nullable = false)
    private String dtNascimento;

    @Column(nullable = false)
    private String nuTelefone;

    @Column(nullable = false)
    private String dsSenha;

    // Construtor vazio necessário para JPA
    public ClientModel() {
    }

    // Getters e Setters
    public Integer getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(Integer id) {
        this.cdCliente = id;
    }

    public String getNmCliente() {
        return nmCliente;
    }

    public void setNmCliente(String nmCliente) {
        this.nmCliente = nmCliente;
    }

    public String getNuCpf() {
        return nuCpf;
    }

    public void setNuCpf(String nuCpf) {
        this.nuCpf = nuCpf;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public String getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(String dtNasc) {
        this.dtNascimento = dtNasc;
    }

    public String getNuTelefone() {
        return nuTelefone;
    }

    public void setNuTelefone(String nuTelefone) {
        this.nuTelefone = nuTelefone;
    }

    public String getDsSenha() {
        return dsSenha;
    }

    public void setDsSenha(String dsSenha) {
        this.dsSenha = dsSenha;
    }
}