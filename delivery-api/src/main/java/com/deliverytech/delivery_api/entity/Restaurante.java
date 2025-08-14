package com.deliverytech.delivery_api.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "restaurante")

public class Restaurante {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "endereco")
    private String endereco;
    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("nome ASC")
    private List<Produto> produtos;    
}
