package com.projetobackend.demo.repository;

import com.projetobackend.demo.models.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, Integer> {
    Optional<PedidoModel> findById(int id);


    List<PedidoModel> findByClienteIdOrderByDataHoraDesc(int id);
}