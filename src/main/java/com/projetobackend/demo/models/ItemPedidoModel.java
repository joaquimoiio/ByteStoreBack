package com.projetobackend.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name="TBITEM_PEDIDO")
public class ItemPedidoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdItemPedido;

    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoModel cdPedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel cdProduto;

    @Column(nullable = false)
    private Integer qtQuantidade;

    @Column(nullable = false)
    private BigDecimal vlPrecoUnitario;

    public ItemPedidoModel() {
    }

    // Getters e Setters
    public Integer getCdItemPedido() {
        return cdItemPedido;
    }

    public void setCdItemPedido(Integer id) {
        this.cdItemPedido = id;
    }

    public PedidoModel getCdPedido() {
        return cdPedido;
    }

    public void setCdPedido(PedidoModel pedido) {
        this.cdPedido = pedido;
    }

    public ProdutoModel getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(ProdutoModel produto) {
        this.cdProduto = produto;
    }

    public Integer getQtQuantidade() {
        return qtQuantidade;
    }

    public void setQtQuantidade(Integer quantidade) {
        this.qtQuantidade = quantidade;
    }

    public BigDecimal getVlPrecoUnitario() {
        return vlPrecoUnitario;
    }

    public void setVlPrecoUnitario(BigDecimal precoUnitario) {
        this.vlPrecoUnitario = precoUnitario;
    }

    // Método para calcular o subtotal do item
    public BigDecimal getSubtotal() {
        return vlPrecoUnitario.multiply(new BigDecimal(qtQuantidade));
    }
}