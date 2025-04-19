package com.projetobackend.demo.repository;

import com.projetobackend.demo.models.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Integer> {
    Optional<ProdutoModel> findById(int id);

    List<ProdutoModel> findByDestaqueTrue();

    List<ProdutoModel> findByCategoria(String categoria);

    List<ProdutoModel> findByNomeContainingIgnoreCase(String nome);
}