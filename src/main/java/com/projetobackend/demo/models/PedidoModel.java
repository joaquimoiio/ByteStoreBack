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
    private ClientModel cliente;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private BigDecimal vlTotal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pedido")
    private List<ItemPedidoModel> itens = new ArrayList<>();

    @Column
    private String cepEntrega;

    @Column
    private String dsEntrega;

    public PedidoModel() {
        this.dataHora = LocalDateTime.now();
        this.status = "Processando";
    }

    // Alterar os getters e setters
    public Integer getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(Integer cdPedido) {
        this.cdPedido = cdPedido;
    }

    public ClientModel getCliente() {
        return cliente;
    }

    public void setCliente(ClientModel cliente) {
        this.cliente = cliente;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getVlTotal() {
        return vlTotal;
    }

    public void setVlTotal(BigDecimal valorTotal) {
        this.vlTotal = valorTotal;
    }

    public List<ItemPedidoModel> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoModel> itens) {
        this.itens = itens;
    }

    public String getCepEntrega() {
        return cepEntrega;
    }

    public void setCepEntrega(String cepEntrega) {
        this.cepEntrega = cepEntrega;
    }

    public String getDsEntrega() {
        return dsEntrega;
    }

    public void setDsEntrega(String enderecoEntrega) {
        this.dsEntrega = enderecoEntrega;
    }

    // Método para calcular o valor total - manter como está
    public void calcularValorTotal() {
        this.vlTotal = this.itens.stream()
                .map(item -> item.getVlUnitario().multiply(new BigDecimal(item.getQtPedido())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}