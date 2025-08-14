package com.deliverytech.delivery_api.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@Table(name = "pedido")

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime dataPedido;
    private String enderecoEntrega;
    private BigDecimal subtotal;
    private BigDecimal taxaEntrega;
    private BigDecimal valorTotal;

      
    @ManyToOne 
    @JoinColumn(name = "restaurante_id")
    private Restaurante restaurante;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
    private List<ItemPedido> itens;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
}
