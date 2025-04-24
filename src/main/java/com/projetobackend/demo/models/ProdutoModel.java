package com.projetobackend.demo.models;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="TBPRODUTO")
public class ProdutoModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cdProduto; // Alterado de id para cdProduto

    @Column(nullable = false)
    private String nmProduto; // Alterado de nome para nmProduto

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal precoAntigo;

    @Column(nullable = false)
    private BigDecimal vlProduto; // Alterado de precoAtual para vlProduto

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private String imagemPrincipal;

    @ElementCollection
    @CollectionTable(name = "TBIMAGENS_GALERIA", joinColumns = @JoinColumn(name = "produto_id"))
    @Column(name = "url_imagem")
    private List<String> imagensGaleria = new ArrayList<>();

    @Column(nullable = false)
    private Boolean destaque;

    public ProdutoModel() {
    }

    public Integer getCdProduto() {
        return cdProduto;
    }

    public void setCdProduto(Integer cdProduto) {
        this.cdProduto = cdProduto;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nmProduto) {
        this.nmProduto = nmProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoAntigo() {
        return precoAntigo;
    }

    public void setPrecoAntigo(BigDecimal precoAntigo) {
        this.precoAntigo = precoAntigo;
    }

    public BigDecimal getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(BigDecimal vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getImagemPrincipal() {
        return imagemPrincipal;
    }

    public void setImagemPrincipal(String imagemPrincipal) {
        this.imagemPrincipal = imagemPrincipal;
    }

    public List<String> getImagensGaleria() {
        return imagensGaleria;
    }

    public void setImagensGaleria(List<String> imagensGaleria) {
        this.imagensGaleria = imagensGaleria;
    }

    public Boolean getDestaque() {
        return destaque;
    }

    public void setDestaque(Boolean destaque) {
        this.destaque = destaque;
    }
}