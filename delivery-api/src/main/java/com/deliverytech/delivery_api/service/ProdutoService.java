package com.deliverytech.delivery_api.service;

import java.util.List;

import com.deliverytech.delivery_api.dto.ProdutoDto;

public interface ProdutoService {
    public long cadastrarProduto(ProdutoDto produtoDto);
    public List<ProdutoDto> buscarProdutosPorRestaurante(Long restauranteId);
    public ProdutoDto buscarProdutoPorId(Long id);
    public ProdutoDto atualizarProduto(Long id, ProdutoDto produtoDto);
    public ProdutoDto alterarDisponibilidade(Long id, boolean disponivel);
    public ProdutoDto buscarProdutosPorCategoria(String categoria);
}
