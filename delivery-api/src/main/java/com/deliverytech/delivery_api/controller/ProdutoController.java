package com.deliverytech.delivery_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery_api.dto.ProdutoDto;
import com.deliverytech.delivery_api.dto.RestauranteDto;
import com.deliverytech.delivery_api.service.ProdutoService;
import com.deliverytech.delivery_api.service.ProdutoServiceImpl;


import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/product")
public class ProdutoController {
    @Autowired
    private ProdutoService produtoService;
     
    @GetMapping
    public List<ProdutoDto> getAllProducts() {        
        // return productService.findAll();
        return null;
    }

    @PostMapping
    public ResponseEntity<Long> cadastrarProduto(@RequestBody ProdutoDto dto) {
        long id = produtoService.cadastrarProduto(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(id);
    }

    @PatchMapping("/{id}/produto")
    public ResponseEntity<ProdutoDto> atualizarProduto(@RequestBody ProdutoDto produtoDto, Long id) {
        ProdutoDto produtoSalvo = produtoService.atualizarProduto(id, produtoDto);
        return ResponseEntity.ok(produtoSalvo);
    }

}
