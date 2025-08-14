package com.deliverytech.delivery_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliverytech.delivery_api.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
