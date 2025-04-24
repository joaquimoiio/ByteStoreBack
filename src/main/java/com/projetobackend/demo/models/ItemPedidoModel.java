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
    private PedidoModel pedido;

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutoModel produto;

    @Column(nullable = false)
    private Integer qtPedido;

    @Column(nullable = false)
    private BigDecimal vlUnitario;

    public ItemPedidoModel() {
    }

    // Alterar os getters e setters
    public Integer getCdItemPedido() {
        return cdItemPedido;
    }

    public void setCdItemPedido(Integer cdItemPedido) {
        this.cdItemPedido = cdItemPedido;
    }

    public PedidoModel getPedido() {
        return pedido;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    public ProdutoModel getProduto() {
        return produto;
    }

    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    public Integer getQtPedido() {
        return qtPedido;
    }

    public void setQtPedido(Integer quantidade) {
        this.qtPedido = quantidade;
    }

    public BigDecimal getVlUnitario() {
        return vlUnitario;
    }

    public void setVlUnitario(BigDecimal precoUnitario) {
        this.vlUnitario = precoUnitario;
    }

    // Método para calcular o subtotal do item - manter como está
    public BigDecimal getSubtotal() {
        return vlUnitario.multiply(new BigDecimal(qtPedido));
    }
}