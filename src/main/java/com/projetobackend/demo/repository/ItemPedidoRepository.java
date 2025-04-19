package com.projetobackend.demo.repository;

import com.projetobackend.demo.models.ItemPedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedidoModel, Integer> {
    List<ItemPedidoModel> findByPedidoId(int pedidoId);
}