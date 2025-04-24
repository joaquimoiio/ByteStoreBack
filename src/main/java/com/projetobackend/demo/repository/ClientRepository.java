package com.projetobackend.demo.repository;

import com.projetobackend.demo.models.ClientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Integer> {
    Optional<ClientModel> findByCdCliente(int cdCliente);

}