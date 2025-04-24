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
    private Integer cdProduto;

    @Column(nullable = false)
    private String nmProduto;

    @Column(nullable = false)
    private String dsDescricao;

    @Column(nullable = false)
    private BigDecimal vlPrecoAntigo;

    @Column(nullable = false)
    private BigDecimal vlPrecoAtual;

    @Column(nullable = false)
    private Integer qtEstoque;

    @Column(nullable = false)
    private String dsCategoria;

    @Column(nullable = false)
    private String imgProduto;

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

    public void setCdProduto(Integer id) {
        this.cdProduto = id;
    }

    public String getNmProduto() {
        return nmProduto;
    }

    public void setNmProduto(String nome) {
        this.nmProduto = nome;
    }

    public String getDsDescricao() {
        return dsDescricao;
    }

    public void setDsDescricao(String descricao) {
        this.dsDescricao = descricao;
    }

    public BigDecimal getVlPrecoAntigo() {
        return vlPrecoAntigo;
    }

    public void setVlPrecoAntigo(BigDecimal precoAntigo) {
        this.vlPrecoAntigo = precoAntigo;
    }

    public BigDecimal getVlPrecoAtual() {
        return vlPrecoAtual;
    }

    public void setVlPrecoAtual(BigDecimal precoAtual) {
        this.vlPrecoAtual = precoAtual;
    }

    public Integer getQtEstoque() {
        return qtEstoque;
    }

    public void setQtEstoque(Integer estoque) {
        this.qtEstoque = estoque;
    }

    public String getDsCategoria() {
        return dsCategoria;
    }

    public void setDsCategoria(String categoria) {
        this.dsCategoria = categoria;
    }

    public String getImgProduto() {
        return imgProduto;
    }

    public void setImgProduto(String imagemPrincipal) {
        this.imgProduto = imagemPrincipal;
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