package com.projetobackend.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="TBCLIENT")
public class ClientModel implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nmCliente;

    @Column(nullable = false)
    private String nuCpf;

    @Column(nullable = false)
    private String dsEmail;

    @Column(nullable = false)
    private String dtNasc;

    @Column(nullable = false)
    private String nuTelefone;

    @Column(nullable = false)
    private String dsSenha;

    // Construtor vazio necessário para JPA
    public ClientModel() {
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
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