package com.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.delivery.entities.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}
