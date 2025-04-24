package com.projetobackend.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TBPEDIDO")
public class PedidoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdPedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClientModel cdCliente;

    @Column(nullable = false)
    private LocalDateTime dtDataHora;

    @Column(nullable = false)
    private String dsStatus;

    @Column(nullable = false)
    private BigDecimal vlTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cdPedido")
    private List<ItemPedidoModel> dsItens = new ArrayList<>();

    @Column
    private String nuCEP;

    @Column
    private String dsEndereco;

    public PedidoModel() {
        this.dtDataHora = LocalDateTime.now();
        this.dsStatus = "Processando";
    }


    public Integer getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Integer id) {
        this.cdPedido = id;
    }

    public ClientModel getCdCliente() {
        return cdCliente;
    }

    public void setCdCliente(ClientModel cliente) {
        this.cdCliente = cliente;
    }

    public LocalDateTime getDtDataHora() {
        return dtDataHora;
    }

    public void setDtDataHora(LocalDateTime dataHora) {
        this.dtDataHora = dataHora;
    }

    public String getDsStatus() {
        return dsStatus;
    }

    public void setDsStatus(String status) {
        this.dsStatus = status;
    }

    public BigDecimal getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(BigDecimal valorTotal) {
        this.vlTotal = valorTotal;
    }

    public List<ItemPedidoModel> getDsItens() {
        return dsItens;
    }

    public void setDsItens(List<ItemPedidoModel> itens) {
        this.dsItens = itens;
    }

    public String getNuCEP() {
        return nuCEP;
    }

    public void setNuCEP(String cepEntrega) {
        this.nuCEP = cepEntrega;
    }

    public String getDsEndereco() {
        return dsEndereco;
    }

    public void setDsEndereco(String enderecoEntrega) {
        this.dsEndereco = enderecoEntrega;
    }

    // Método para calcular o valor total
    public void calcularValorTotal() {
        this.vlTotal = this.dsItens.stream()
                .map(item -> item.getVlPrecoUnitario().multiply(new BigDecimal(item.getQtQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}