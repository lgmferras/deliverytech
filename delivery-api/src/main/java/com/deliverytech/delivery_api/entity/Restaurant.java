package com.deliverytech.delivery_api.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "restaurante")

public class Restaurant {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "endereco")
    private String endereco;
}
