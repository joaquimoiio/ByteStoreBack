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
    private String dsProduto;

    @Column(nullable = false)
    private BigDecimal vlAntigo;

    @Column(nullable = false)
    private BigDecimal vlProduto;

    @Column(nullable = false)
    private Integer dsEstoque;

    @Column(nullable = false)
    private String dsCategoria;

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

    public String getDsProduto() {
        return dsProduto;
    }

    public void setDsProduto(String descricao) {
        this.dsProduto = descricao;
    }

    public BigDecimal getVlAntigo() {
        return vlAntigo;
    }

    public void setVlAntigo(BigDecimal precoAntigo) {
        this.vlAntigo = precoAntigo;
    }

    public BigDecimal getVlProduto() {
        return vlProduto;
    }

    public void setVlProduto(BigDecimal vlProduto) {
        this.vlProduto = vlProduto;
    }

    public Integer getDsEstoque() {
        return dsEstoque;
    }

    public void setDsEstoque(Integer estoque) {
        this.dsEstoque = estoque;
    }

    public String getDsCategoria() {
        return dsCategoria;
    }

    public void setDsCategoria(String categoria) {
        this.dsCategoria = categoria;
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